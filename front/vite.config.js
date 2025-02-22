import { fileURLToPath, URL } from 'node:url'
import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import vueDevTools from 'vite-plugin-vue-devtools'

export default defineConfig({
  plugins: [vue()],
  server: {
    port: 8081,
    proxy: {
      '/api': {
        target: 'http://localhost:8080', // Ваш бэкенд сервер
        changeOrigin: true, // Для корректной работы с CORS
        secure: false, // Если не используете HTTPS
      },
    },
  },
});
