package com.poli.servicios.pedidos.dominio;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

/**
 * Entidad JPA para un pedido.
 */
@Entity
@Table(name = "pedido")
public class Pedido {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private Long clienteId;

  private OffsetDateTime fecha;

  private String estado;

  private BigDecimal total;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getClienteId() {
    return clienteId;
  }

  public void setClienteId(Long clienteId) {
    this.clienteId = clienteId;
  }

  public OffsetDateTime getFecha() {
    return fecha;
  }

  public void setFecha(OffsetDateTime fecha) {
    this.fecha = fecha;
  }

  public String getEstado() {
    return estado;
  }

  public void setEstado(String estado) {
    this.estado = estado;
  }

  public BigDecimal getTotal() {
    return total;
  }

  public void setTotal(BigDecimal total) {
    this.total = total;
  }
}