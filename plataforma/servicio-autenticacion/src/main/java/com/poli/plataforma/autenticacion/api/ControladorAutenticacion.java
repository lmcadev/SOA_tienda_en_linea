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
    // Comparación de contraseñas simple
    if (!usuario.getContrasena().equals(peticion.getContrasena())) {
      return ResponseEntity.status(401).build();
    }
    String token = servicioToken.generarToken(usuario.getUsuario(), usuario.getRoles());
    return ResponseEntity.ok(new RespuestaAutenticacion(token));
  }
}