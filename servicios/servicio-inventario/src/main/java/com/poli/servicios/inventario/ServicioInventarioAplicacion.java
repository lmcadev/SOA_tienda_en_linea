package com.poli.servicios.inventario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Punto de entrada del servicio de inventario.
 */
@SpringBootApplication
public class ServicioInventarioAplicacion {
  public static void main(String[] args) {
    SpringApplication.run(ServicioInventarioAplicacion.class, args);
  }
}