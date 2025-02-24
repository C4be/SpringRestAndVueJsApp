<template>
    <h1>Список всех клеток</h1>
    <CellViewer title="home1"/><br/>
    <CellViewer title="home"/>
</template>

<script>
import CellViewer from '../components/Cells/CellViewer.vue';

export default {
    components: {
        CellViewer
    }
}
</script>