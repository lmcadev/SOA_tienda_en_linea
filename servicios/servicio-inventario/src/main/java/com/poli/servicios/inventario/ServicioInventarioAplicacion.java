package com.poli.servicios.inventario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Punto de entrada del servicio de inventario.
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.poli.servicios.inventario"})
@EnableJpaRepositories(basePackages = {"com.poli.servicios.inventario.dominio"})
public class ServicioInventarioAplicacion {
  public static void main(String[] args) {
    SpringApplication.run(ServicioInventarioAplicacion.class, args);
  }
}