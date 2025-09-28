package com.poli.servicios.inventario.config;

import com.poli.servicios.inventario.entidad.ProductoInventario;
import com.poli.servicios.inventario.dominio.ProductoInventarioRepositorio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * Inicializador de datos para productos en inventario.
 */
@Component
@Order(1)
public class InicializadorDatosInventario implements ApplicationRunner {
    
    private static final Logger logger = LoggerFactory.getLogger(InicializadorDatosInventario.class);
    
    private final ProductoInventarioRepositorio repositorio;
    
    public InicializadorDatosInventario(ProductoInventarioRepositorio repositorio) {
        this.repositorio = repositorio;
    }
    
    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.info("Verificando inicialización de productos en inventario...");
        long totalProductos = repositorio.count();
        logger.info("Total de productos en inventario: {}", totalProductos);
        
        if (totalProductos == 0) {
            logger.info("🇨🇴 Inicializando productos colombianos en inventario...");
            crearProductosColombianos();
            logger.info("Productos colombianos inicializados exitosamente");
        } else {
            logger.info("El inventario ya contiene productos, omitiendo inicialización");
        }
    }
    
    private void crearProductosColombianos() {
        List<ProductoInventario> productos = Arrays.asList(
            // Café colombiano
            crearProducto("CAFE-001", "Café Juan Valdez Premium", 50, "Bodega A1"),
            crearProducto("CAFE-002", "Café Águila Roja Tostado", 75, "Bodega B2"),
            crearProducto("CAFE-003", "Café La Bastilla Especial", 40, "Bodega C1"),
            
            // Productos agrícolas
            crearProducto("FRUTA-001", "Plátano Hartón Maduro", 200, "Bodega F1"),
            crearProducto("FRUTA-002", "Aguacate Hass Premium", 80, "Bodega F2"),
            crearProducto("FRUTA-003", "Mango Tommy Atkins", 120, "Bodega F3"),
            crearProducto("FRUTA-004", "Piña", 90, "Bodega F4"),
            
            // Artesanías colombianas
            crearProducto("ARTE-001", "Mochila", 25, "Bodega A2"),
            crearProducto("ARTE-002", "Sombrero Vueltiao", 30, "Bodega A3"),
            crearProducto("ARTE-003", "Hamaca", 15, "Bodega A4"),
            
            // Productos de panadería
            crearProducto("PAN-001", "Arepa Paisa Congelada", 100, "Bodega P1"),
            crearProducto("PAN-002", "Pandebono Valluno", 80, "Bodega P2"),
            crearProducto("PAN-003", "Buñuelos Tradicionales", 60, "Bodega P3"),
            
            // Bebidas típicas
            crearProducto("BEB-001", "Aguapanela con Limón", 150, "Bodega B1"),
            crearProducto("BEB-002", "Chicha Morada Natural", 70, "Bodega B2"),
            crearProducto("BEB-003", "Avena Quaker Colombiana", 90, "Bodega B3"),
            
            // Dulces y confites
            crearProducto("DULCE-001", "Bocadillo Veleño", 120, "Bodega D1"),
            crearProducto("DULCE-002", "Arequipe Alpina", 85, "Bodega D2"),
            crearProducto("DULCE-003", "Cocadas Isleñas", 45, "Bodega D3"),
            
            // Productos cárnicos
            crearProducto("CARNE-001", "Chorizo Santarrosano", 40, "Bodega C2"),
            crearProducto("CARNE-002", "Morcilla Antioqueña", 35, "Bodega C3"),
            crearProducto("CARNE-003", "Chicharrón Boyacense", 50, "Bodega C4"),
            
            // Productos lácteos
            crearProducto("LACTEO-001", "Queso Costeño Salado", 60, "Bodega L1"),
            crearProducto("LACTEO-002", "Cuajada Campesina", 45, "Bodega L2"),
            crearProducto("LACTEO-003", "Yogurt Alpina Kumis", 100, "Bodega L3")
        );
        
        repositorio.saveAll(productos);
        logger.info("Se crearon {} productos colombianos en el inventario", productos.size());
    }
    
    private ProductoInventario crearProducto(String codigo, String nombre, int cantidad, String ubicacion) {
        ProductoInventario producto = new ProductoInventario();
        producto.setCodigoProducto(codigo);
        producto.setNombre(nombre);
        producto.setCantidad(cantidad);
        producto.setUbicacion(ubicacion);
        producto.setActualizadoEn(OffsetDateTime.now());
        return producto;
    }
}