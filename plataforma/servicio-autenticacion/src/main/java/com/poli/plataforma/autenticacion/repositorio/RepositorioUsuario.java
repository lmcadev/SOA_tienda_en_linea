package com.poli.plataforma.autenticacion.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.poli.plataforma.autenticacion.entidad.Usuario;

import java.util.Optional;

/**
 * Repositorio para la entidad Usuario.
 */
@Repository
public interface RepositorioUsuario extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByUsuario(String usuario);
}