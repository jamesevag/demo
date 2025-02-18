package de.adesso.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.configurationSource(request -> {
                    var corsConfig = new org.springframework.web.cors.CorsConfiguration();
                    corsConfig.addAllowedOriginPattern("*"); // Allows requests from any origin
                    corsConfig.addAllowedMethod("*"); // Allows all HTTP methods
                    corsConfig.addAllowedHeader("*"); // Allows all headers
                    return corsConfig;
                }))
                .csrf(csrf -> csrf.disable()) // ✅ Disabling CSRF since it's not needed for APIs
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/**").permitAll() // ✅ Allow public access to APIs
                        .anyRequest().authenticated() // ✅ Secure all other requests
                );

        return http.build();
    }
}
