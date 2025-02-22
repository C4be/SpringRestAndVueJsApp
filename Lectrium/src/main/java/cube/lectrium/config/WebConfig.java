package cube.lectrium.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3002")  // Разрешаем доступ с фронтенда
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // Все нужные методы
                .allowedHeaders("*")  // Разрешаем все заголовки
                .allowCredentials(true);  // Разрешаем передачу cookies
    }
}
