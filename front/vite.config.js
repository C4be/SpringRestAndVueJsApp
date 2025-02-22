import { fileURLToPath, URL } from 'node:url'
import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import vueDevTools from 'vite-plugin-vue-devtools'

export default defineConfig({
  plugins: [
    vue(),
    vueDevTools(),
  ],
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url))
    },
  },
  server: {
    port: 8080, // Убедитесь, что порт фронтенда правильный
    open: true,
    proxy: {
      '/api': {
        target: 'http://localhost:8080', // Ваш бэкенд сервер
        changeOrigin: true,  // Для корректной работы с CORS
        secure: false,  // Если не используете HTTPS
        rewrite: (path) => path.replace(/^\/api/, ''),  // Убираем /api при проксировании
      },
    },
  }
})
