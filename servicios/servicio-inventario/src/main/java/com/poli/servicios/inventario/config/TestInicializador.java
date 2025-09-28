package com.poli.servicios.inventario.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuración simple para probar la ejecución de comandos al inicio.
 */
@Configuration
public class TestInicializador {
    
    private static final Logger logger = LoggerFactory.getLogger(TestInicializador.class);
    
    @Bean
    public CommandLineRunner testRunner() {
        return args -> {
            logger.info("============================================");
            logger.info("TEST INICIALIZADOR EJECUTADO CORRECTAMENTE");
            logger.info("============================================");
        };
    }
}