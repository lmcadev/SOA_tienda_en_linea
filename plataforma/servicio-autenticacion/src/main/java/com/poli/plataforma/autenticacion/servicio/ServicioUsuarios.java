package com.poli.plataforma.autenticacion.servicio;

import com.poli.plataforma.autenticacion.entidad.Usuario;
import com.poli.plataforma.autenticacion.repositorio.RepositorioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;

/**
 * Servicio para gestionar usuarios con persistencia en base de datos.
 */
@Service
public class ServicioUsuarios {
  
  @Autowired
  private RepositorioUsuario repositorioUsuario;
  
  @Autowired
  private PasswordEncoder passwordEncoder;

  /**
   * Busca un usuario por su nombre de usuario.
   * 
   * @param usuario el nombre del usuario a buscar
   * @return Optional con el usuario si existe, vacío en caso contrario
   */
  public Optional<Usuario> buscarPorUsuario(String usuario) {
    return repositorioUsuario.findByUsuario(usuario);
  }
  
  /**
   * Valida si la contraseña proporcionada coincide con la contraseña cifrada del usuario
   */
  public boolean validarContrasena(String contrasenaPlana, String contrasenaSecreta) {
    return passwordEncoder.matches(contrasenaPlana, contrasenaSecreta);
  }
  
  /**
   * Crea un nuevo usuario con contraseña cifrada.
   * 
   * @param nombreUsuario el nombre del usuario
   * @param contrasena la contraseña en texto plano (será cifrada automáticamente)
   * @param roles los roles asignados al usuario
   * @return el usuario creado y guardado en la base de datos
   */
  public Usuario crearUsuario(String nombreUsuario, String contrasena, HashSet<String> roles) {
    Usuario nuevoUsuario = new Usuario(nombreUsuario, passwordEncoder.encode(contrasena), roles);
    return repositorioUsuario.save(nuevoUsuario);
  }
  
  /**
   * Verifica si existe un usuario con el nombre especificado.
   * 
   * @param nombreUsuario el nombre del usuario a verificar
   * @return true si el usuario existe, false en caso contrario
   */
  public boolean existeUsuario(String nombreUsuario) {
    return repositorioUsuario.findByUsuario(nombreUsuario).isPresent();
  }
  
  /**
   * Obtiene el número total de usuarios registrados.
   * 
   * @return el número de usuarios en la base de datos
   */
  public long contarUsuarios() {
    return repositorioUsuario.count();
  }
  
  /**
   * Actualiza la contraseña de un usuario existente.
   * 
   * @param nombreUsuario el nombre del usuario
   * @param nuevaContrasena la nueva contraseña en texto plano
   * @return true si se actualizó correctamente, false si el usuario no existe
   */
  public boolean actualizarContrasena(String nombreUsuario, String nuevaContrasena) {
    Optional<Usuario> usuarioOpt = repositorioUsuario.findByUsuario(nombreUsuario);
    if (usuarioOpt.isPresent()) {
      Usuario usuario = usuarioOpt.get();
      usuario.setContrasena(passwordEncoder.encode(nuevaContrasena));
      repositorioUsuario.save(usuario);
      return true;
    }
    return false;
  }
}