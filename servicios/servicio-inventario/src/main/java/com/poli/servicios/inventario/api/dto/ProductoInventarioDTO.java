package com.poli.servicios.inventario.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DTO para operaciones de inventario.
 */
@Schema(name = "ProductoInventario", description = "Representación de un producto en el inventario")
public class ProductoInventarioDTO {
  @Schema(description = "Identificador del producto", example = "1")
  public Long id;

  @Schema(description = "Código único del producto", example = "SKU-ABC-123")
  public String codigoProducto;

  @Schema(description = "Nombre del producto", example = "Boquilla 1.5mm")
  public String nombre;

  @Schema(description = "Cantidad disponible", example = "50")
  public Integer cantidad;

  @Schema(description = "Ubicación física", example = "Bodega A - Estante 3")
  public String ubicacion;
}