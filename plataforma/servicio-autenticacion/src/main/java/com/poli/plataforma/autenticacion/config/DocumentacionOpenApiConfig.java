package com.poli.plataforma.autenticacion.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

/**
 * Configuración de documentación OpenAPI para el servicio de autenticación.
 */
@OpenAPIDefinition(
    info = @Info(
        title = "API de Autenticación",
        version = "1.0",
        description = "Servicio que emite tokens JWT para la plataforma",
        contact = @Contact(name = "Soporte", email = "soporte@empresa.com")
    ),
    servers = {
        @Server(url = "/", description = "Servidor predeterminado")
    }
)
@Configuration
public class DocumentacionOpenApiConfig {
}