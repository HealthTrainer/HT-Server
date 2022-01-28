package com.healthtrainer.htserver.config;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .exposedHeaders("X_AUTH_TOKEN")
                .allowCredentials(true)
                .allowedOrigins("http://localhost:3000");
    }
}
