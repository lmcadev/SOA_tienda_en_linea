package com.poli.plataforma.autenticacion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Servicio de autenticación responsable de generar JWT.
 */
@SpringBootApplication
public class ServicioAutenticacionAplicacion {
  public static void main(String[] args) {
    SpringApplication.run(ServicioAutenticacionAplicacion.class, args);
  }
}