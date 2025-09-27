package com.poli.servicios.pagos.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
    info = @Info(
        title = "API de Pagos",
        version = "1.0",
        description = "Servicios de gestión de pagos"
    ),
    servers = {@Server(url = "/", description = "Servidor por defecto")}
)
@Configuration
public class DocumentacionOpenApiConfig {
}