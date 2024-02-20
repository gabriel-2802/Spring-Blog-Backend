package com.app.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Apply to all endpoints
                .allowedOrigins("http://localhost:3000") // Allow frontend origin
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Allowable methods
                .allowedHeaders("*") // Allow all headers
                .allowCredentials(true); // Allow credentials
    }
}
