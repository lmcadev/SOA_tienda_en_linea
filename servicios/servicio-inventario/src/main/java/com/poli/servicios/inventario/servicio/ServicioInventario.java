package com.poli.servicios.inventario.servicio;

import com.poli.servicios.inventario.api.dto.ProductoInventarioDTO;
import com.poli.servicios.inventario.dominio.ProductoInventarioRepositorio;
import com.poli.servicios.inventario.entidad.ProductoInventario;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;

/**
 * Servicio de dominio para el inventario.
 */
@Service
public class ServicioInventario {

  private final ProductoInventarioRepositorio repo;

  public ServicioInventario(ProductoInventarioRepositorio repo) {
    this.repo = repo;
  }

  @Transactional
  public ProductoInventario crear(ProductoInventarioDTO dto) {
    if (repo.existsByCodigoProducto(dto.codigoProducto)) {
      throw new IllegalArgumentException("El código de producto ya existe");
    }
    ProductoInventario p = new ProductoInventario();
    p.setCodigoProducto(dto.codigoProducto);
    p.setNombre(dto.nombre);
    p.setCantidad(dto.cantidad);
    p.setUbicacion(dto.ubicacion);
    p.setActualizadoEn(OffsetDateTime.now());
    return repo.save(p);
  }

  public Page<ProductoInventario> listar(int pagina, int tamanio, Sort sort) {
    return repo.findAll(PageRequest.of(pagina, tamanio, sort));
  }

  public ProductoInventario obtener(Long id) {
    return repo.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("No existe el producto con id " + id));
  }

  @Transactional
  public ProductoInventario actualizar(Long id, ProductoInventarioDTO dto) {
    ProductoInventario p = obtener(id);
    if (dto.codigoProducto != null) p.setCodigoProducto(dto.codigoProducto);
    if (dto.nombre != null) p.setNombre(dto.nombre);
    if (dto.cantidad != null) p.setCantidad(dto.cantidad);
    if (dto.ubicacion != null) p.setUbicacion(dto.ubicacion);
    p.setActualizadoEn(OffsetDateTime.now());
    return repo.save(p);
  }

  @Transactional
  public void eliminar(Long id) {
    repo.deleteById(id);
  }
}