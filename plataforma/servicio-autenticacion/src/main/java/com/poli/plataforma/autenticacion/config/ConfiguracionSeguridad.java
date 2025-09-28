package com.poli.plataforma.autenticacion.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Configuración de seguridad para el servicio de autenticación
 */
@Configuration
@EnableWebSecurity
public class ConfiguracionSeguridad {

    /**
     * Bean para el encoder de contraseñas usando BCrypt
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Configuración de la cadena de filtros de seguridad
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Deshabilitar CSRF para APIs REST
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Sin sesiones
            .authorizeHttpRequests(authz -> authz
                // Permitir acceso público a endpoints de autenticación
                .requestMatchers("/auth/**").permitAll()
                // Permitir acceso público a endpoints de inicialización
                .requestMatchers("/api/inicializacion/**").permitAll()
                // Permitir acceso público a documentación Swagger
                .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html").permitAll()
                // Permitir acceso público a actuator
                .requestMatchers("/actuator/**").permitAll()
                // Cualquier otra petición requiere autenticación
                .anyRequest().authenticated()
            );

        return http.build();
    }
}