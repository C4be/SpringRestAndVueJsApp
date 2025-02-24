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
        <!-- <nav>
            <ul>
                <li>
                    <RouterLink to="/">Перейти к home</RouterLink><br/>
                    <RouterLink to="/cell/home">Перейти к Examplehome</RouterLink><br/>
                    <RouterLink to="/cell/home1">Перейти к Examplehome1</RouterLink>
                </li>
            </ul>
        </nav> -->

        <nav class="navbar navbar-expand-lg navbar-dark bg-primary sticky-top">
            <div class="container">
                <RouterLink class="navbar-brand" to="/">Главная</RouterLink>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
                    aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <RouterLink class="nav-link" to="/cells">
                                <i class="bi bi-house-door"></i> Cells
                            </RouterLink>
                        </li>
                        <li class="nav-item">
                            <RouterLink class="nav-link" to="/cell/home">
                                <i class="bi bi-house-door"></i> Examplehome
                            </RouterLink>
                        </li>
                        <li class="nav-item">
                            <RouterLink class="nav-link" to="/cell/home1">
                                <i class="bi bi-house-fill"></i> Examplehome1
                            </RouterLink>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>

        <p class="">
            <strong>Current route path:</strong> {{ $route.fullPath }}
        </p>

        <main class="d-flex flex-column justify-content-center align-items-center">
            <RouterView />
        </main>


    </div>
</template>

<style></style>