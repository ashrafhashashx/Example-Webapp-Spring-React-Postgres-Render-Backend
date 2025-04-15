package com.example.taskmanager;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class CORSConfig {

    @Bean
    public CorsFilter corsFilter() {
        // Create the CORS configuration
        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.setAllowCredentials(true);
        corsConfig.addAllowedOrigin("https://my-react-app-py6s.onrender.com"); // React app's URL
        corsConfig.addAllowedHeader("*");
        corsConfig.addAllowedMethod("*");

        // Register the CORS configuration with the URL
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/api/**", corsConfig); // Apply to all API endpoints

        return new CorsFilter(source);
    }
}
