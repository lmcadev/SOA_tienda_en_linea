package com.poli.servicios.inventario.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

/**
 * Configuración de OpenAPI para el servicio de inventario.
 */
@OpenAPIDefinition(
    info = @Info(
        title = "API de Inventario",
        version = "1.0",
        description = "Servicios de gestión del inventario de productos"
    ),
    servers = {@Server(url = "/", description = "Servidor por defecto")}
)
@Configuration
public class DocumentacionOpenApiConfig {
}