<template>
    <h1>Hello body!</h1><br/>
    <CellViewer title="home1"/><br/>
    <CellViewer title="home"/>
</template>

<script>
import CellViewer from '../components/Cells/CellViewer.vue';
import CellComponent from '../components/CellComponent.vue';

export default {
    components: {
        CellViewer
    }
}

</script>