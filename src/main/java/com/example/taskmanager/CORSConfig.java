package com.example.taskmanager;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class CORSConfig {

    // This gets its value from application.properties file:
    @Value("${my.cors.config.allowing.react.app}") String myCorsConfigForTheReactApp;

    @Bean
    public CorsFilter corsFilter() {
        // Create the CORS configuration
        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.setAllowCredentials(true);
        corsConfig.addAllowedOrigin("https://example-webapp-spring-react-postgres-7za6.onrender.com");
        corsConfig.addAllowedOrigin(myCorsConfigForTheReactApp); // React app on my computer
        corsConfig.addAllowedHeader("*");
        corsConfig.addAllowedMethod("*");

        // Register the CORS configuration with the URL
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/api/**", corsConfig); // Apply to all API endpoints

        return new CorsFilter(source);
    }
}
