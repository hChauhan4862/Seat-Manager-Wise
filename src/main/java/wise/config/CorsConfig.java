package wise.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import wise.filters.MyInterceptor;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Autowired
    private MyInterceptor myInterceptor;

    // In CorsConfig.java
    @Value("${allowed.origins}")
    private String[] allowedOrigins;


    @Override
    public void addCorsMappings(@SuppressWarnings("null") CorsRegistry registry) {
        registry.addMapping("/**") // Allow all endpoints
            .allowedOrigins(allowedOrigins) // Replace with actual origins
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Allow all HTTP methods
            .allowedHeaders("*") // Allow all headers
            .allowCredentials(true); // Allow credentials (cookies, authorization headers)
    }

    @Override
    public void addInterceptors(@SuppressWarnings("null") InterceptorRegistry registry) {
        registry.addInterceptor(myInterceptor).addPathPatterns("/**"); // Apply the interceptor to all routes
    }
}
