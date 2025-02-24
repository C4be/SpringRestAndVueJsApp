<template>
    <div class="card shadow-lg mx-auto mt-4" style="width: 50rem;">
        <div v-if="cell">
            <div class="card-body">
                <h1 class="card-title text-center">
                    {{ cell.title }} ({{ cell.cellType }})
                    <button @click="toggleEdit" class="btn btn-sm btn-outline-primary ms-2">✏️</button>
                </h1>
                <div class="card-text">
                    <template v-if="isEditing">
                        <textarea v-model="editableContent" class="form-control" rows="5"></textarea>
                        <div class="mt-2">
                            <button @click="saveChanges" class="btn btn-success btn-sm">Сохранить</button>
                            <button @click="cancelEdit" class="btn btn-secondary btn-sm ms-2">Отмена</button>
                        </div>
                    </template>
                    <template v-else>
                        <pre v-if="cell.cellType === 'PYTHON_CEIL' || cell.cellType === 'JAVA_CEIL'"><code :class="codeClass" v-html="highlightedContent"></code></pre>
                        <div v-else-if="cell.cellType === 'MARKDOWN_CEIL'" v-html="markdownContent"></div>
                        <p v-else>{{ cell.content }}</p>
                    </template>
                </div>
            </div>
        </div>
        <div v-else class="text-center p-4">
            <p>Загрузка...</p>
        </div>
    </div>
</template>

<script>
import { ref, onMounted, watch, computed } from 'vue';
import hljs from 'highlight.js';
import 'highlight.js/styles/github-dark.css';
import { marked } from 'marked';

export default {
    props: {
        title: {
            type: String,
            required: true
        }
    },
    setup(props) {
        const cell = ref(null);
        const isEditing = ref(false);
        const editableContent = ref('');

        const fetchCell = async () => {
            try {
                const response = await fetch(`http://localhost:8080/api/cell/${props.title}`);
                if (response.ok) {
                    cell.value = await response.json();
                } else {
                    console.error('Ошибка загрузки ячейки');
                }
            } catch (error) {
                console.error('Ошибка при получении ячейки:', error);
            }
        };

        onMounted(fetchCell);
        watch(() => props.title, fetchCell);

        const toggleEdit = () => {
            isEditing.value = !isEditing.value;
            if (isEditing.value) {
                editableContent.value = cell.value.content;
            }
        };

        const cancelEdit = () => {
            isEditing.value = false;
        };

        const saveChanges = async () => {
            try {
                const response = await fetch(`http://localhost:8080/api/cell/${props.title}/update`, {
                    method: 'PUT',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify({
                        content: editableContent.value,
                        cellType: cell.value.cellType
                    })
                });
                if (response.ok) {
                    cell.value.content = editableContent.value;
                    isEditing.value = false;
                } else {
                    console.error('Ошибка при обновлении ячейки');
                }
            } catch (error) {
                console.error('Ошибка при сохранении:', error);
            }
        };

        const codeClass = computed(() => {
            if (cell.value?.cellType === 'PYTHON_CEIL') return 'language-python';
            if (cell.value?.cellType === 'JAVA_CEIL') return 'language-java';
            return '';
        });

        const highlightedContent = computed(() => {
            if (!cell.value || !cell.value.content) return '';
            return hljs.highlight(cell.value.content.trim(), { language: codeClass.value.replace('language-', '') }).value;
        });

        const markdownContent = computed(() => {
            return cell.value?.cellType === 'MARKDOWN_CEIL' ? marked(cell.value.content) : '';
        });

        return { cell, codeClass, highlightedContent, markdownContent, isEditing, editableContent, toggleEdit, cancelEdit, saveChanges };
    }
};
</script>
