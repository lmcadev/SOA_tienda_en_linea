package com.poli.servicios.pagos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Servicio de Pagos
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.poli.servicios.pagos"})
@EnableJpaRepositories(basePackages = {"com.poli.servicios.pagos.dominio"})
public class ServicioPagosAplicacion {
  public static void main(String[] args) {
    SpringApplication.run(ServicioPagosAplicacion.class, args);
  }
}