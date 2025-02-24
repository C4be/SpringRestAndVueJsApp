<template>

    <div class="card shadow-lg mx-auto mt-4" style="width: 50rem;">
        <div v-if="cell">
            <div class="card-body">

                <h5 class="card-title text-center">{{ cell.title }}</h5>
                <h6 class="card-subtitle mb-2 text-body-secondary">{{ cell.cellType }}</h6>
                <button @click="toggleEdit" class="btn btn-sm btn-outline-primary ms-2">✏️</button>
                
                <!-- Редактирование -->
                <CellEditor v-if="isEditing" :content="cell.content" :cellType="cell.cellType" @save="saveChanges"
                    @cancel="cancelEdit" />
                
                <!-- Отображение в нужном стиле -->
                <template v-else>
                    <CodeCell v-if="isCode" :content="cell.content" :language="cell.cellType" />
                    <MarkdownCell v-else-if="cell.cellType === 'MARKDOWN_CELL'" :content="cell.content" />
                    <p v-else>{{ cell.content }}</p>
                </template>

            </div>
        </div>
        <div v-else class="text-center p-4">
            <p>Загрузка...</p>
        </div>
    </div>

</template>

<script>
import { ref, computed, onMounted, watch } from 'vue';
import CellEditor from './CellEditor.vue';
import CodeCell from './CodeCell.vue';
import MarkdownCell from './MarkdownCell.vue';

export default {
    props: { title: String },
    components: { CellEditor, CodeCell, MarkdownCell },
    setup(props) {
        const cell = ref(null);
        const isEditing = ref(false);

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

        const toggleEdit = () => (isEditing.value = !isEditing.value);
        const cancelEdit = () => (isEditing.value = false);
        const saveChanges = async (newContent) => {
            try {
                const response = await fetch(`http://localhost:8080/api/cell/${props.title}/update`, {
                    method: 'PUT',
                    headers: { 'Content-Type': 'application/json' },
                    // Упаковка в JSON
                    body: JSON.stringify({ content: newContent, cellType: cell.value.cellType })
                });
                if (response.ok) {
                    cell.value.content = newContent;
                    isEditing.value = false;
                } else {
                    console.error('Ошибка при обновлении');
                }
            } catch (error) {
                console.error('Ошибка при сохранении:', error);
            }
        };

        // проверка на то, что у нас код
        const isCode = computed(() => ['PYTHON_CELL', 'JAVA_CELL'].includes(cell.value?.cellType));
        console.log(`Посчитали: ${cell.cellType}`);

        return { cell, isEditing, toggleEdit, cancelEdit, saveChanges, isCode };
    }
};
</script>
