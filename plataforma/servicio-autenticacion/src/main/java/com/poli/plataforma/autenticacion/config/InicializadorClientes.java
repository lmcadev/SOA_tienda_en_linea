package com.poli.plataforma.autenticacion.config;

import com.poli.plataforma.autenticacion.entidad.Usuario;
import com.poli.plataforma.autenticacion.repositorio.RepositorioUsuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Inicializador de datos para clientes.
 */
@Component
@Order(2) // Se ejecuta después del DataInitializer principal
public class InicializadorClientes implements CommandLineRunner {
    
    private static final Logger logger = LoggerFactory.getLogger(InicializadorClientes.class);
    
    private final RepositorioUsuario repositorioUsuario;
    private final PasswordEncoder passwordEncoder;
    
    public InicializadorClientes(RepositorioUsuario repositorioUsuario, PasswordEncoder passwordEncoder) {
        this.repositorioUsuario = repositorioUsuario;
        this.passwordEncoder = passwordEncoder;
    }
    
    @Override
    public void run(String... args) throws Exception {
        logger.info("Verificando inicialización de clientes colombianos...");
        long totalUsuarios = repositorioUsuario.count();
        logger.info("Total de usuarios en base de datos: {}", totalUsuarios);
        
        // Verificar si ya existen clientes colombianos específicos
        boolean tiendaPaisaExiste = repositorioUsuario.findByUsuario("tienda.paisa").isPresent();
        
        if (!tiendaPaisaExiste) {
            logger.info("🇨🇴 Inicializando clientes colombianos...");
            crearClientesColombianos();
            logger.info("Clientes colombianos inicializados exitosamente");
        } else {
            logger.info("ℹLos clientes colombianos ya existen, omitiendo inicialización");
        }
    }
    
    private void crearClientesColombianos() {
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
            crearClienteEmpresarial("frutas.urabá", "empresa123", "Frutas del Urabá"),
            crearClienteEmpresarial("dulces.santander", "empresa123", "Dulces de Santander")
        );
        
        repositorioUsuario.saveAll(clientes);
        logger.info("Se crearon {} clientes colombianos", clientes.size());
    }
    
    private Usuario crearCliente(String nombreUsuario, String contrasena, String comentario) {
        logger.info("Creando cliente: {}", comentario);
        Set<String> roles = new HashSet<>();
        roles.add("ROLE_USER");
        roles.add("ROLE_CLIENTE");
        return new Usuario(nombreUsuario, passwordEncoder.encode(contrasena), roles);
    }
    
    private Usuario crearClienteEmpresarial(String nombreUsuario, String contrasena, String comentario) {
        logger.info("Creando cliente empresarial: {}", comentario);
        Set<String> roles = new HashSet<>();
        roles.add("ROLE_USER");
        roles.add("ROLE_CLIENTE");
        roles.add("ROLE_EMPRESA");
        return new Usuario(nombreUsuario, passwordEncoder.encode(contrasena), roles);
    }
}