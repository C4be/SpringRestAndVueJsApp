<template>
    <h1>Hello, body!</h1><br/>
    
    <div v-html="renderedMarkdown"></div>

</template>

<script>
import { marked } from 'marked';

export default {
    props: { content: String },
    setup() {
        const renderedMarkdown = marked.parse(
            "# Hello, this is my new project!\n" + 
            "#### my name Dima!\n" +
            "- one\n" +
            "- two\n" +
            "- three\n" +
            "- four\n" +
            "- five\n"
        );
        return { renderedMarkdown };
    }
};
</script>