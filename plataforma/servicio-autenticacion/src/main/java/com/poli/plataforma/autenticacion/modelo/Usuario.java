package com.poli.plataforma.autenticacion.modelo;

import java.util.Set;

/**
 * Representa un usuario simple del sistema.
 */
public class Usuario {
  private String usuario;
  private String contrasena;
  private Set<String> roles;

  public Usuario() {
  }

  public Usuario(String usuario, String contrasena, Set<String> roles) {
    this.usuario = usuario;
    this.contrasena = contrasena;
    this.roles = roles;
  }

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

  public Set<String> getRoles() {
    return roles;
  }

  public void setRoles(Set<String> roles) {
    this.roles = roles;
  }
}