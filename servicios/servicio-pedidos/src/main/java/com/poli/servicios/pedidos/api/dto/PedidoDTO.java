package com.poli.servicios.pedidos.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Schema(name = "Pedido", description = "Datos de un pedido")
public class PedidoDTO {
  @Schema(description = "Identificador del pedido", example = "1")
  public Long id;

  @Schema(description = "Identificador del cliente", example = "100")
  public Long clienteId;

  @Schema(description = "Fecha de creación", example = "2024-06-05T12:30:00Z")
  public OffsetDateTime fecha;

  @Schema(description = "Estado del pedido", example = "CREADO")
  public String estado;

  @Schema(description = "Total del pedido", example = "99.99")
  public BigDecimal total;
}