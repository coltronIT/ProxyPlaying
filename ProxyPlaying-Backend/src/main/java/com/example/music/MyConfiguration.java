package com.example.music; // This is a subpackage of your main application package

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Objects;

@Configuration
public class MyConfiguration {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                Objects.requireNonNull(registry, "CorsRegistry must not be null");
                registry.addMapping("/api/**").allowedOrigins("http://localhost:5173");
            }
        };
    }
}


