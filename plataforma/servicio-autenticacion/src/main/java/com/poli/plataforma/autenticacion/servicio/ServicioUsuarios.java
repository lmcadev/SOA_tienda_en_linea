package com.empresa.plataforma.autenticacion.servicio;

import com.empresa.plataforma.autenticacion.modelo.Usuario;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * Servicio simple para gestionar usuarios en memoria.
 */
@Service
public class ServicioUsuarios {
  private final Map<String, Usuario> usuarios = new HashMap<>();

  public ServicioUsuarios() {
    // Usuarios de ejemplo: usuario1 (CLIENTE), admin (ADMIN), operador (OPERADOR)
    usuarios.put("usuario1", new Usuario("usuario1", "secreto1", new HashSet<>(Set.of("CLIENTE"))));
    usuarios.put("admin", new Usuario("admin", "admin123", new HashSet<>(Set.of("ADMIN"))));
    usuarios.put("operador", new Usuario("operador", "operador123", new HashSet<>(Set.of("OPERADOR"))));
  }

  public Optional<Usuario> buscarPorUsuario(String usuario) {
    return Optional.ofNullable(usuarios.get(usuario));
  }
}