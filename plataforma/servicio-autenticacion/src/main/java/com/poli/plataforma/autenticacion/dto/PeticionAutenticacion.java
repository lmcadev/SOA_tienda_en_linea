package com.poli.plataforma.autenticacion.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

/**
 * DTO para la petición de login.
 */
@Schema(name = "PeticionAutenticacion", description = "Credenciales para iniciar sesión")
public class PeticionAutenticacion {

  @Schema(description = "Nombre de usuario", example = "usuario1", required = true)
  @NotBlank
  private String usuario;

  @Schema(description = "Contraseña", example = "secreto", required = true)
  @NotBlank
  private String contrasena;

  public String getUsuario() {
    return usuario;
  }

  public void setUsuario(String usuario) {
    this.usuario = usuario;
  }

  public String getContrasena() {
    return contrasena;
  }

  public void setContrasena(String contrasena) {
    this.contrasena = contrasena;
  }
}