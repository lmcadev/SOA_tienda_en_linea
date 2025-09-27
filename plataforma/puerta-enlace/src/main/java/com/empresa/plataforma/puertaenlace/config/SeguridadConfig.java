package com.empresa.plataforma.puertaenlace.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

/**
 * Configuración de seguridad para Gateway.
 */
@Configuration
public class SeguridadConfig {

  @Bean
  public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
    // Desactiva CSRF y utiliza el filtro JWT para autorización
    http
        .csrf(ServerHttpSecurity.CsrfSpec::disable)
        .authorizeExchange(exchanges -> exchanges
            .pathMatchers("/auth/**", "/v3/api-docs/**", "/swagger-ui/**", "/actuator/health", "/actuator/info").permitAll()
            .anyExchange().permitAll()
        );
    return http.build();
  }
}