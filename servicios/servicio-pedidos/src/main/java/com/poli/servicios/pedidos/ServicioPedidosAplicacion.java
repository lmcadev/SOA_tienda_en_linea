package com.poli.servicios.pedidos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Servicio de Pedidos
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.poli.servicios.pedidos"})
public class ServicioPedidosAplicacion {
  public static void main(String[] args) {
    SpringApplication.run(ServicioPedidosAplicacion.class, args);
  }
}