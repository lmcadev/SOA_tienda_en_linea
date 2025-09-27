package com.poli.servicios.inventario.dominio;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

/**
 * Repositorio JPA para Productos de Inventario.
 */
public interface ProductoInventarioRepositorio extends JpaRepository<ProductoInventario, Long> {
  Optional<ProductoInventario> findByCodigoProducto(String codigoProducto);
  boolean existsByCodigoProducto(String codigoProducto);
}