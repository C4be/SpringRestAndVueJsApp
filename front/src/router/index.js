import { createRouter, createWebHistory } from 'vue-router';
import CellComponent from '../components/CellComponent.vue'; // Импорт компонента CellDetail
import Cell from '../views/Cell.vue';
import Home from '../views/Home.vue';

const routes = [
  {
    path: '/',
    component: Home,
  },
  {
    path: '/cell/:title',
    component: CellComponent,
    props: true // Это позволит передавать параметр title как пропс в компонент
  },
  {
    path: '/cells/',
    component: Cell,
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes
});

export default router;
