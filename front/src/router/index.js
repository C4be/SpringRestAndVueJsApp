import { createRouter, createMemoryHistory } from 'vue-router';
import CellComponent from '../components/CellComponent.vue'; // Импорт компонента CellDetail

const routes = [
  {
    path: '/cell/:title',
    component: CellComponent,
    props: true // Это позволит передавать параметр title как пропс в компонент
  },
  {
    path: '/cell/ExampleTitle',
    component: CellComponent,
    props: true // Это позволит передавать параметр title как пропс в компонент
  },
];

const router = createRouter({
  history: createMemoryHistory(),
  routes
});

export default router;
