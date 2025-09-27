package com.poli.plataforma.autenticacion.config;

import com.poli.plataforma.autenticacion.modelo.Usuario;
import com.poli.plataforma.autenticacion.repositorio.RepositorioUsuario;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Collections;

/**
 * Inicializador de datos por defecto para el sistema de autenticación.
 * Se ejecuta al arrancar la aplicación y crea usuarios por defecto si no existen.
 */
@Component
public class DataInitializer {
    
    private static final Logger logger = LoggerFactory.getLogger(DataInitializer.class);
    
    @Autowired
    private RepositorioUsuario repositorioUsuario;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    /**
     * Inicializa los usuarios por defecto del sistema si no existen.
     * Se ejecuta automáticamente después de la construcción del bean.
     */
    @PostConstruct
    public void inicializarDatos() {
        logger.info("Iniciando verificación de usuarios por defecto...");
        
        if (repositorioUsuario.count() == 0) {
            crearUsuariosPorDefecto();
        } else {
            logger.info("Los usuarios ya existen en la base de datos. Total: {}", repositorioUsuario.count());
        }
    }
    
    /**
     * Crea los usuarios por defecto del sistema con contraseñas cifradas.
     */
    private void crearUsuariosPorDefecto() {
        logger.info("Creando usuarios por defecto con contraseñas cifradas...");
        
        try {
            // Usuario cliente por defecto
            Usuario usuario1 = new Usuario(
                "usuario1", 
                passwordEncoder.encode("secreto1"), 
                new HashSet<>(Collections.singleton("CLIENTE"))
            );
            
            // Usuario administrador
            Usuario admin = new Usuario(
                "admin", 
                passwordEncoder.encode("admin123"), 
                new HashSet<>(Collections.singleton("ADMIN"))
            );
            
            // Usuarios operadores
            Usuario operador = new Usuario(
                "operador", 
                passwordEncoder.encode("operador123"), 
                new HashSet<>(Collections.singleton("OPERADOR"))
            );
            
            Usuario operador2 = new Usuario(
                "operador2", 
                passwordEncoder.encode("operador123"), 
                new HashSet<>(Collections.singleton("OPERADOR"))
            );
            
            // Guardar usuarios en la base de datos
            repositorioUsuario.save(usuario1);
            repositorioUsuario.save(admin);
            repositorioUsuario.save(operador);
            repositorioUsuario.save(operador2);
            
            logger.info("Usuarios por defecto creados exitosamente:");
            logger.info("   - usuario1 (CLIENTE)");
            logger.info("   - admin (ADMIN)");
            logger.info("   - operador (OPERADOR)");
            logger.info("   - operador2 (OPERADOR)");
            
        } catch (Exception e) {
            logger.error("Error al crear usuarios por defecto: {}", e.getMessage(), e);
        }
    }
    
    /**
     * Verifica si un usuario específico existe en la base de datos.
     * 
     * @param nombreUsuario el nombre del usuario a verificar
     * @return true si el usuario existe, false en caso contrario
     */
    public boolean existeUsuario(String nombreUsuario) {
        return repositorioUsuario.findByUsuario(nombreUsuario).isPresent();
    }
    
    /**
     * Obtiene el número total de usuarios en la base de datos.
     * 
     * @return el número de usuarios registrados
     */
    public long contarUsuarios() {
        return repositorioUsuario.count();
    }
}