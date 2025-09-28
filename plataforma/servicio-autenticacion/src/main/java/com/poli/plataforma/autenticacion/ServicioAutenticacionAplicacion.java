package com.poli.plataforma.autenticacion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Servicio de autenticación responsable de generar JWT.
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.poli.plataforma.autenticacion"})
public class ServicioAutenticacionAplicacion {
  public static void main(String[] args) {
    SpringApplication.run(ServicioAutenticacionAplicacion.class, args);
  }
}