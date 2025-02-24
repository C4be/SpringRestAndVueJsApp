<template>
    <div v-html="renderedMarkdown"></div>
</template>

<script>
import { computed } from 'vue';
import { marked } from 'marked';

export default {
    props: { content: String },
    setup(props) {
        const renderedMarkdown = computed(() => marked(props.content));
        return { renderedMarkdown };
    }
};
</script>
