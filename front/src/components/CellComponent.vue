<template>
    <div class="card shadow-lg mx-auto mt-4" style="width: 30rem;">
        <div v-if="cell">
            <div class="card-body">
                <h1 class="card-title text-center">{{ cell.title }} ({{ cell.cellType }})</h1>
                <div class="card-text">
                    <pre v-if="cell.cellType === 'PYTHON_CEIL' || cell.cellType === 'JAVA_CEIL'"><code :class="codeClass" v-html="highlightedContent"></code></pre>
                    <div v-else-if="cell.cellType === 'MARKDOWN_CEIL'" v-html="markdownContent"></div>
                    <p v-else>{{ cell.content }}</p>
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

        const codeClass = computed(() => {
            if (cell.value?.cellType === 'PYTHON_CEIL') return 'language-python';
            if (cell.value?.cellType === 'JAVA_CEIL') return 'language-java';
            return '';
        });

        const highlightedContent = computed(() => {
            if (!cell.value || !cell.value.content) return '';
            const formattedCode = cell.value.content.trim(); // Заменяем табуляцию на пробелы
            return hljs.highlight(formattedCode, { language: codeClass.value.replace('language-', '') }).value;
        });


        const markdownContent = computed(() => {
            return cell.value?.cellType === 'MARKDOWN_CEIL' ? marked(cell.value.content) : '';
        });

        return { cell, codeClass, highlightedContent, markdownContent };
    }
};
</script>
