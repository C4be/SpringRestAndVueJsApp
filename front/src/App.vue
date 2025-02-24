<script>
import { RouterLink, RouterView } from 'vue-router';

export default {
    name: 'App',
    components: {
        RouterLink,
        RouterView
    },
};
</script>

<template>
    <div id="app">
        <h1>Мое приложение с маршрутизацией</h1>

        <nav>
            <ul>
                <li>
                    <RouterLink to="/">Перейти к home</RouterLink><br/>
                    <RouterLink to="/cell/home">Перейти к Examplehome</RouterLink><br/>
                    <RouterLink to="/cell/home1">Перейти к Examplehome1</RouterLink>
                </li>
            </ul>
        </nav>

        <p>
            <strong>Current route path:</strong> {{ $route.fullPath }}
        </p>

        <main class="d-flex flex-column justify-content-center align-items-center">
            <RouterView />
        </main>
        

    </div>
</template>

<style>
/* Добавьте свои стили */
nav {
    margin: 20px 0;
}

ul {
    list-style-type: none;
}

li {
    margin-bottom: 10px;
}
</style>