<template>

    <div class="card" style="width: 30rem;">

        <div v-if="cell">
            <div class="card-body">
                <h1 class="card-title">{{ cell.title }} ({{ cell.cellType }})</h1>
            </div>

            <div class="card-text">
                {{ cell.content }}
            </div>
        </div>
        <div v-else>
            <div class="card">
                <div class="card-body">
                    <!-- <h1 class="card-title">{{ cell.title }}</h1> -->
                    <div class="card-text">
                        <p>Загрузка...</p>
                    </div>
                </div>
            </div>
        </div>

    </div>


</template>

<script>
import { ref, onMounted, watch } from 'vue';

export default {
    props: {
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
                const response = await fetch(`http://localhost:8080/api/cell/${props.title}`, {
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

        watch(() => props.title, () => {
            fetchCell();
        });

        // Возвращаем состояние, чтобы оно было доступно в шаблоне
        return {
            cell
        };
    }
};
</script>