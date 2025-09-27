package com.poli.servicios.notificaciones.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.OffsetDateTime;

@Schema(name = "Notificacion", description = "Datos de una notificación")
public class NotificacionDTO {
  @Schema(description = "Identificador de la notificación", example = "1")
  public Long id;
  @Schema(description = "Tipo de notificación", example = "EMAIL")
  public String tipo;
  @Schema(description = "Destinatario de la notificación", example = "cliente@correo.com")
  public String destinatario;
  @Schema(description = "Mensaje de la notificación", example = "Su pedido fue enviado")
  public String mensaje;
  @Schema(description = "Estado de entrega", example = "ENVIADO")
  public String estadoEntrega;
  @Schema(description = "Fecha de emisión", example = "2024-06-05T16:30:00Z")
  public OffsetDateTime fecha;
}