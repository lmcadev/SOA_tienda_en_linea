package com.poli.plataforma.autenticacion.servicio;

import com.poli.plataforma.autenticacion.modelo.Usuario;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Collections;

/**
 * Servicio simple para gestionar usuarios en memoria.
 */
@Service
public class ServicioUsuarios {
  private final Map<String, Usuario> usuarios = new HashMap<>();

  public ServicioUsuarios() {
    usuarios.put("usuario1", new Usuario("usuario1", "secreto1", new HashSet<>(Collections.singleton("CLIENTE"))));
    usuarios.put("admin", new Usuario("admin", "admin123", new HashSet<>(Collections.singleton("ADMIN"))));
    usuarios.put("operador", new Usuario("operador", "operador123", new HashSet<>(Collections.singleton("OPERADOR"))));
    usuarios.put("operador", new Usuario("operador2", "operador123", new HashSet<>(Collections.singleton("OPERADOR"))));
  }

  public Optional<Usuario> buscarPorUsuario(String usuario) {
    return Optional.ofNullable(usuarios.get(usuario));
  }
}