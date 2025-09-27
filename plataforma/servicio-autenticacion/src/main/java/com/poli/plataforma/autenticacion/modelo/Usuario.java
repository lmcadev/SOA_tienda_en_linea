package com.poli.plataforma.autenticacion.modelo;

import jakarta.persistence.*;
import java.util.Set;

/**
 * Representa un usuario simple del sistema.
 */
@Entity
@Table(name = "usuarios")
public class Usuario {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  @Column(unique = true, nullable = false)
  private String usuario;
  
  @Column(nullable = false)
  private String contrasena;
  
  @ElementCollection(fetch = FetchType.EAGER)
  @CollectionTable(name = "usuario_roles", joinColumns = @JoinColumn(name = "usuario_id"))
  @Column(name = "rol")
  private Set<String> roles;

  public Usuario() {
  }

  public Usuario(String usuario, String contrasena, Set<String> roles) {
    this.usuario = usuario;
    this.contrasena = contrasena;
    this.roles = roles;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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