package com.poli.plataforma.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Servidor de descubrimiento Eureka.
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaServidorAplicacion {

  public static void main(String[] args) {
    SpringApplication.run(EurekaServidorAplicacion.class, args);
  }
}