package com.poli.plataforma.autenticacion.config;

import com.poli.plataforma.autenticacion.entidad.Usuario;
import com.poli.plataforma.autenticacion.repositorio.RepositorioUsuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * Inicializador de clientes colombianos.
 * Se ejecuta cuando la aplicación está completamente lista.
 */
@Component
@Order(2)
public class InicializadorClientesColombianos implements ApplicationRunner {
    
    private static final Logger logger = LoggerFactory.getLogger(InicializadorClientesColombianos.class);
    
    @Autowired
    private RepositorioUsuario repositorioUsuario;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    /**
     * Se ejecuta cuando la aplicación está completamente inicializada.
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.info("🇨🇴 Verificando inicialización de clientes colombianos...");
        
        // Verificar si ya existen clientes colombianos
        if (!repositorioUsuario.findByUsuario("tienda.paisa").isPresent()) {
            logger.info("Creando clientes colombianos...");
            crearClientesColombianos();
            logger.info("Clientes colombianos creados exitosamente");
        } else {
            logger.info("ℹLos clientes colombianos ya existen en la base de datos");
        }
    }
    
    /**
     * Crea clientes colombianos con datos reales de diferentes regiones.
     */
    private void crearClientesColombianos() {
        try {
            List<Usuario> clientes = Arrays.asList(
                // Clientes de diferentes regiones de Colombia
                crearCliente("maria.gonzalez", "cliente123", "María González - Bogotá"),
                crearCliente("carlos.rodriguez", "cliente123", "Carlos Rodríguez - Medellín"),
                crearCliente("ana.martinez", "cliente123", "Ana Martínez - Cali"),
                crearCliente("jose.ramirez", "cliente123", "José Ramírez - Barranquilla"),
                crearCliente("lucia.perez", "cliente123", "Lucía Pérez - Cartagena"),
                crearCliente("david.lopez", "cliente123", "David López - Bucaramanga"),
                crearCliente("sofia.hernandez", "cliente123", "Sofía Hernández - Santa Marta"),
                crearCliente("miguel.torres", "cliente123", "Miguel Torres - Manizales"),
                crearCliente("valentina.castro", "cliente123", "Valentina Castro - Pereira"),
                crearCliente("alejandro.morales", "cliente123", "Alejandro Morales - Armenia"),
                crearCliente("camila.vargas", "cliente123", "Camila Vargas - Ibagué"),
                crearCliente("sebastian.jimenez", "cliente123", "Sebastián Jiménez - Pasto"),
                crearCliente("isabella.ruiz", "cliente123", "Isabella Ruiz - Neiva"),
                crearCliente("mateo.gutierrez", "cliente123", "Mateo Gutiérrez - Villavicencio"),
                crearCliente("gabriela.sanchez", "cliente123", "Gabriela Sánchez - Tunja"),
                
                // Clientes empresariales
                crearClienteEmpresarial("tienda.paisa", "empresa123", "Tienda Paisa - Medellín"),
                crearClienteEmpresarial("cafe.colombia", "empresa123", "Café de Colombia Export"),
                crearClienteEmpresarial("artesanias.wayuu", "empresa123", "Artesanías Wayuu La Guajira"),
                crearClienteEmpresarial("frutas.uraba", "empresa123", "Frutas del Urabá"),
                crearClienteEmpresarial("dulces.santander", "empresa123", "Dulces de Santander")
            );
            
            repositorioUsuario.saveAll(clientes);
            logger.info("Se crearon {} clientes colombianos exitosamente", clientes.size());
            
        } catch (Exception e) {
            logger.error("Error al crear clientes colombianos: {}", e.getMessage(), e);
        }
    }
    
    private Usuario crearCliente(String nombreUsuario, String contrasena, String comentario) {
        logger.debug("Creando cliente: {}", comentario);
        HashSet<String> roles = new HashSet<>();
        roles.add("ROLE_USER");
        roles.add("ROLE_CLIENTE");
        return new Usuario(nombreUsuario, passwordEncoder.encode(contrasena), roles);
    }
    
    private Usuario crearClienteEmpresarial(String nombreUsuario, String contrasena, String comentario) {
        logger.debug("Creando cliente empresarial: {}", comentario);
        HashSet<String> roles = new HashSet<>();
        roles.add("ROLE_USER");
        roles.add("ROLE_CLIENTE");
        roles.add("ROLE_EMPRESA");
        return new Usuario(nombreUsuario, passwordEncoder.encode(contrasena), roles);
    }
}