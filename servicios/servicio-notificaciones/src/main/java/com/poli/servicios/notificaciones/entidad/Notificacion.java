package com.poli.servicios.notificaciones.entidad;

import jakarta.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Table(name = "notificacion")
public class Notificacion {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String tipo;

  private String destinatario;

  private String mensaje;

  private String estadoEntrega;

  private OffsetDateTime fecha;

  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }
  public String getTipo() {
    return tipo;
  }
  public void setTipo(String tipo) {
    this.tipo = tipo;
  }
  public String getDestinatario() {
    return destinatario;
  }
  public void setDestinatario(String destinatario) {
    this.destinatario = destinatario;
  }
  public String getMensaje() {
    return mensaje;
  }
  public void setMensaje(String mensaje) {
    this.mensaje = mensaje;
  }
  public String getEstadoEntrega() {
    return estadoEntrega;
  }
  public void setEstadoEntrega(String estadoEntrega) {
    this.estadoEntrega = estadoEntrega;
  }
  public OffsetDateTime getFecha() {
    return fecha;
  }
  public void setFecha(OffsetDateTime fecha) {
    this.fecha = fecha;
  }
}