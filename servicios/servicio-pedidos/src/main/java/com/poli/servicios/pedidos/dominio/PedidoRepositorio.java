package com.poli.servicios.pedidos.dominio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poli.servicios.pedidos.entidad.Pedido;

/**
 * Repositorio JPA para pedidos.
 */
public interface PedidoRepositorio extends JpaRepository<Pedido, Long> {
}