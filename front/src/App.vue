<script>
export default {
    name: 'App',
};
</script>

<template>
    <div id="app">
        <h1>Мое приложение с маршрутизацией</h1>

        <nav>
            <ul>
                <li>
                    <RouterLink to="/cell/ExampleTitle">Перейти к ExampleTitle</RouterLink>
                    <RouterLink to="/cell/home">Перейти к ExampleTitle</RouterLink>
                </li>
            </ul>
        </nav>

        <p>
            <strong>Current route path:</strong> {{ $route.fullPath }}
        </p>

        <main>
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