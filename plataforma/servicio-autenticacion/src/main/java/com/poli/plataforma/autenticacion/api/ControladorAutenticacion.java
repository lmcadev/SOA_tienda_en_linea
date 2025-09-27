package com.poli.plataforma.autenticacion.api;

import com.poli.plataforma.autenticacion.dto.PeticionAutenticacion;
import com.poli.plataforma.autenticacion.dto.RespuestaAutenticacion;
import com.poli.plataforma.autenticacion.modelo.Usuario;
import com.poli.plataforma.autenticacion.servicio.ServicioToken;
import com.poli.plataforma.autenticacion.servicio.ServicioUsuarios;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Controlador REST para autenticación.
 */
@Tag(name = "Autenticacion", description = "Operaciones de login y autenticación")
@RestController
@RequestMapping("/auth")
@Validated
public class ControladorAutenticacion {

  private final ServicioUsuarios servicioUsuarios;
  private final ServicioToken servicioToken;

  public ControladorAutenticacion(ServicioUsuarios servicioUsuarios, ServicioToken servicioToken) {
    this.servicioUsuarios = servicioUsuarios;
    this.servicioToken = servicioToken;
  }

  @Operation(summary = "Iniciar sesión", description = "Devuelve un token JWT si las credenciales son válidas")
  @PostMapping("/login")
  public ResponseEntity<RespuestaAutenticacion> login(@Valid @RequestBody PeticionAutenticacion peticion) {
    Optional<Usuario> usuarioOpt = servicioUsuarios.buscarPorUsuario(peticion.getUsuario());
    if (!usuarioOpt.isPresent()) {
      return ResponseEntity.status(401).build();
    }
    Usuario usuario = usuarioOpt.get();
    // Validar contraseña usando BCrypt
    if (!servicioUsuarios.validarContrasena(peticion.getContrasena(), usuario.getContrasena())) {
      return ResponseEntity.status(401).build();
    }
    String token = servicioToken.generarToken(usuario.getUsuario(), usuario.getRoles());
    return ResponseEntity.ok(new RespuestaAutenticacion(token));
  }

  @Operation(summary = "Registrar usuario", description = "Crea un nuevo usuario con contraseña cifrada")
  @PostMapping("/registro")
  public ResponseEntity<String> registrarUsuario(@Valid @RequestBody PeticionAutenticacion peticion) {
    // Verificar que el usuario no exista
    if (servicioUsuarios.buscarPorUsuario(peticion.getUsuario()).isPresent()) {
      return ResponseEntity.badRequest().body("El usuario ya existe");
    }
    
    // Crear usuario con rol CLIENTE por defecto y contraseña cifrada
    java.util.HashSet<String> roles = new java.util.HashSet<>();
    roles.add("CLIENTE");
    servicioUsuarios.crearUsuario(peticion.getUsuario(), peticion.getContrasena(), roles);
    
    return ResponseEntity.ok("Usuario registrado exitosamente");
  }
}