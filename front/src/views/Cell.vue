<template>
    <div>
        <h1>Список всех клеток</h1>

        <!-- Перебор клеток с добавлением кнопки между ними -->
        <div v-for="(cell, index) in cells" :key="cell.title">
            <CellViewer :title="cell.title" />

            <!-- Добавление кнопки после каждой клетки, кроме последней -->
            <AddCellButton v-if="index < cells.length - 1" @added="fetchCells" />
            <br />
        </div>

        <!-- Кнопка для добавления нового Cell в конце списка -->
        <AddCellButton @added="fetchCells" />
    </div>
</template>

<script>
import { ref, onMounted } from 'vue';
import CellViewer from '../components/Cells/CellViewer.vue';
import AddCellButton from '../components/Cells/AddCellButton.vue';

export default {
    components: {
        CellViewer,
        AddCellButton
    },
    setup() {
        const cells = ref([]);

        // Функция для получения всех клеток с сервера
        const fetchCells = async () => {
            try {
                const response = await fetch('http://localhost:8080/api/cell/all');
                if (response.ok) {
                    cells.value = await response.json();
                } else {
                    console.error('Ошибка при загрузке клеток');
                }
            } catch (error) {
                console.error('Ошибка при получении данных:', error);
            }
        };

        // Получаем данные при монтировании компонента
        onMounted(fetchCells);

        return {
            cells,
            fetchCells
        };
    }
};
</script>