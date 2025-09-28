package com.poli.servicios.pagos.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Schema(name = "Pago", description = "Información de un pago")
public class PagoDTO {
  @Schema(description = "Identificador del pago", example = "1")
  public Long id;
  @Schema(description = "Identificador del pedido asociado", example = "5")
  public Long pedidoId;
  @Schema(description = "Monto pagado", example = "150000.00")
  public BigDecimal monto;
  @Schema(description = "Medio de pago", example = "NEQUI")
  public String medioPago;
  @Schema(description = "Estado del pago", example = "APROBADO")
  public String estado;
  @Schema(description = "Fecha de pago", example = "2024-06-05T15:00:00Z")
  public OffsetDateTime fecha;
}