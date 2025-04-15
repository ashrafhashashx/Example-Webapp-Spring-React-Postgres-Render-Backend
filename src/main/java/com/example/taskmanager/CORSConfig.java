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
        // The following line is basically who is allowed to use this backend. When I deploy it on Render, for example,
        // I will just set an environment variable when filling out the form on the website. instead of just hard-coding at
        // something like this:
        // corsConfig.addAllowedOrigin("https://my-react-app-py6s.onrender.com"); // React app's URL on Render for example
        // So, this is just for the local testing:
        corsConfig.addAllowedOrigin("http://localhost:3000"); // React app on my computer
        corsConfig.addAllowedOrigin("${CORSCONFIG_ALLOWING_REACT_APP}"); // React app on my computer
        corsConfig.addAllowedHeader("*");
        corsConfig.addAllowedMethod("*");

        // Register the CORS configuration with the URL
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/api/**", corsConfig); // Apply to all API endpoints

        return new CorsFilter(source);
    }
}
