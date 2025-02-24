<template>
    <div class="text-center my-4">
        <button class="btn btn-lg btn-outline-success" @click="toggleForm">
            <i class="bi bi-plus-circle"></i> Добавить новый Cell
        </button>

        <div v-if="isFormVisible" class="mt-4">
            <form @submit.prevent="submitForm">
                <div class="mb-3">
                    <label for="title" class="form-label">Заголовок нового Cell</label>
                    <input id="title" v-model="newCell.title" class="form-control" type="text"
                        placeholder="Введите заголовок" required />
                </div>

                <div class="mb-3">
                    <label for="content" class="form-label">Содержание нового Cell</label>
                    <textarea id="content" v-model="newCell.content" class="form-control" rows="3" required></textarea>
                </div>

                <div class="mb-3">
                    <label for="cellType" class="form-label">Тип Cell</label>
                    <select id="cellType" v-model="newCell.cellType" class="form-select" required>
                        <option value="MARKDOWN_CELL">Markdown</option>
                        <option value="PYTHON_CELL">Python</option>
                        <option value="JAVA_CELL">Java</option>
                    </select>
                </div>

                <button type="submit" class="btn btn-primary">Сохранить</button>
                <button type="button" class="btn btn-secondary ms-2" @click="cancelForm">Отмена</button>
            </form>
        </div>
    </div>
</template>

<script>
import { ref } from 'vue';

export default {
    setup(props, { emit }) {
        const isFormVisible = ref(false);
        const newCell = ref({
            title: '',
            content: '',
            cellType: 'MARKDOWN_CEIL'
        });

        // Функция для показа или скрытия формы
        const toggleForm = () => {
            isFormVisible.value = !isFormVisible.value;
        };

        // Функция для сброса формы
        const cancelForm = () => {
            isFormVisible.value = false;
            newCell.value.title = '';
            newCell.value.content = '';
            newCell.value.cellType = 'MARKDOWN_CEIL';
        };

        // Функция для отправки формы
        const submitForm = async () => {
            try {
                const response = await fetch(`http://localhost:8080/api/cell/${newCell.value.title}/create`, {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify({
                        content: newCell.value.content,
                        cellType: newCell.value.cellType
                    })
                });

                if (response.ok) {
                    // Эмитируем событие после успешного добавления
                    emit('added');
                    cancelForm(); // Закрываем форму после успешного добавления
                } else {
                    console.error('Ошибка при добавлении нового Cell');
                }
            } catch (error) {
                console.error('Ошибка при отправке запроса:', error);
            }
        };

        return {
            isFormVisible,
            newCell,
            toggleForm,
            cancelForm,
            submitForm
        };
    }
};
</script>