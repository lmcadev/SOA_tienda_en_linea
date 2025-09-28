package com.poli.servicios.inventario.entidad;

import jakarta.persistence.*;
import java.time.OffsetDateTime;

/**
 * Entidad JPA para productos en inventario.
 */
@Entity
@Table(name = "producto_inventario")
public class ProductoInventario {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true)
  private String codigoProducto;

  @Column(nullable = false)
  private String nombre;

  @Column(nullable = false)
  private Integer cantidad;

  private String ubicacion;
  private OffsetDateTime actualizadoEn;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getCodigoProducto() {
    return codigoProducto;
  }

  public void setCodigoProducto(String codigoProducto) {
    this.codigoProducto = codigoProducto;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public Integer getCantidad() {
    return cantidad;
  }

  public void setCantidad(Integer cantidad) {
    this.cantidad = cantidad;
  }

  public String getUbicacion() {
    return ubicacion;
  }

  public void setUbicacion(String ubicacion) {
    this.ubicacion = ubicacion;
  }

  public OffsetDateTime getActualizadoEn() {
    return actualizadoEn;
  }

  public void setActualizadoEn(OffsetDateTime actualizadoEn) {
    this.actualizadoEn = actualizadoEn;
  }
}