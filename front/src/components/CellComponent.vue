<template>
    <div v-if="cell">
        <!-- Заголовок ячейки -->
        <h1>{{ cell.title }} ({{ cell.cellType }})</h1>

        <!-- Содержание ячейки -->
        <div>
            {{ cell.content }}
        </div>
    </div>
    <div v-else>
        <p>Загрузка...</p>
    </div>
</template>

<script>
import { ref, onMounted } from 'vue';

export default {
    props: {
        // Принятие данных о ячейке через пропс
        title: {
            type: String,
            required: true
        }
    },
    setup(props) {
        // Используем ref для хранения данных о ячейке
        const cell = ref(null);

        // Функция для получения данных ячейки по title
        const fetchCell = async () => {
            try {
                const response = await fetch(`/api/cell/${props.title}`, {
                    method: 'GET',
                });
                if (response.ok) {
                    cell.value = await response.json();
                } else {
                    console.error('Ошибка загрузки ячейки');
                }
            } catch (error) {
                console.error('Ошибка при получении ячейки:', error);
            }
        };


        // Загружаем данные о ячейке при монтировании компонента
        onMounted(() => {
            fetchCell();
        });

        // Возвращаем состояние, чтобы оно было доступно в шаблоне
        return {
            cell
        };
    }
};
</script>

<style scoped>
/* Стили для компонента отображения ячейки */
h1 {
    color: #333;
    font-size: 1.5em;
}

div {
    margin-top: 10px;
    font-size: 1.1em;
    color: #555;
}
</style>