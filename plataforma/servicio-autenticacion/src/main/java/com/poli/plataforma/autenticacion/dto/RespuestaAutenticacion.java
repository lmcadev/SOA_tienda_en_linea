package com.poli.plataforma.autenticacion.dto;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DTO para la respuesta de autenticación.
 */
@Schema(name = "RespuestaAutenticacion", description = "Respuesta con token JWT")
public class RespuestaAutenticacion {

  @Schema(description = "Token JWT generado", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6...")
  private String token;

  public RespuestaAutenticacion() {
  }

  public RespuestaAutenticacion(String token) {
    this.token = token;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }
}