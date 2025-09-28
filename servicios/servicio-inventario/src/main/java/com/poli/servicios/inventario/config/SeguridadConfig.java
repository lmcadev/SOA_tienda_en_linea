package com.poli.servicios.inventario.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Configuración de seguridad para el servicio de inventario.
 */
@Configuration
public class SeguridadConfig {
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/actuator/health", "/actuator/info").permitAll()
            .requestMatchers("/api/inicializacion/**").permitAll()
            .anyRequest().permitAll()
        );
    return http.build();
  }
}