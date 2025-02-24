<template>
    <pre><code :class="codeClass" v-html="highlightedCode"></code></pre>
</template>

<script>
import { computed } from 'vue';
import hljs from 'highlight.js';
import 'highlight.js/styles/github-dark.css';

export default {
    props: { content: String, language: String },
    setup(props) {
        const codeClass = computed(() => `language-${props.language.toLowerCase().replace('_ceil', '')}`);
        const highlightedCode = computed(() => {
            return hljs.highlight(props.content.trim(), { language: codeClass.value.replace('language-', '') }).value;
        });

        return { codeClass, highlightedCode };
    }
};
</script>
