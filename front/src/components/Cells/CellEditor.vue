<template>
    <div>
        <textarea v-model="contentLocal" class="form-control" rows="5"></textarea>
        <div class="mt-2">
            <button @click="save" class="btn btn-success btn-sm">Сохранить</button>
            <button @click="$emit('cancel')" class="btn btn-secondary btn-sm ms-2">Отмена</button>
        </div>
    </div>
</template>

<script>
import { ref, watch } from 'vue';

export default {
    props: { content: String, cellType: String },
    emits: ['save', 'cancel'],
    setup(props, { emit }) {
        const contentLocal = ref(props.content);

        watch(() => props.content, (newVal) => {
            contentLocal.value = newVal;
        });

        const save = () => {
            emit('save', contentLocal.value);
        };

        return { contentLocal, save };
    }
};
</script>
