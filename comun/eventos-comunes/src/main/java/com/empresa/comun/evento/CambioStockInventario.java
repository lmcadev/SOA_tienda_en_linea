package com.empresa.comun.evento;

/**
 * Evento publicado cuando se modifica el stock en el servicio de inventario.
 */
public class CambioStockInventario {

  private Long productoId;
  private Integer nuevaCantidad;

  public CambioStockInventario() {
  }

  public CambioStockInventario(Long productoId, Integer nuevaCantidad) {
    this.productoId = productoId;
    this.nuevaCantidad = nuevaCantidad;
  }

  public Long getProductoId() {
    return productoId;
  }

  public void setProductoId(Long productoId) {
    this.productoId = productoId;
  }

  public Integer getNuevaCantidad() {
    return nuevaCantidad;
  }

  public void setNuevaCantidad(Integer nuevaCantidad) {
    this.nuevaCantidad = nuevaCantidad;
  }
}