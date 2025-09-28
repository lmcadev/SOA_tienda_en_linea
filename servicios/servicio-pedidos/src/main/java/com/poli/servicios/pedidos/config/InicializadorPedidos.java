package com.poli.servicios.pedidos.config;

import com.poli.servicios.pedidos.entidad.Pedido;
import com.poli.servicios.pedidos.dominio.PedidoRepositorio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * Inicializador de datos para pedidos de clientes colombianos.
 */
@Component
@Order(3)
public class InicializadorPedidos implements ApplicationRunner {
    
    private static final Logger logger = LoggerFactory.getLogger(InicializadorPedidos.class);
    
    @Autowired
    private PedidoRepositorio pedidoRepositorio;
    
    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.info("Verificando inicialización de pedidos colombianos...");
        
        if (pedidoRepositorio.count() == 0) {
            logger.info("Creando pedidos de clientes colombianos...");
            crearPedidosColombianos();
            logger.info("Pedidos colombianos creados exitosamente");
        } else {
            logger.info("ℹYa existen pedidos, omitiendo inicialización");
        }
    }
    
    private void crearPedidosColombianos() {
        try {
            List<Pedido> pedidos = Arrays.asList(
                // Pedidos recientes (últimos 30 días)
                crearPedido(1L, "PROCESANDO", new BigDecimal("125000.00"), -5),
                crearPedido(2L, "ENVIADO", new BigDecimal("89500.00"), -10),
                crearPedido(3L, "ENTREGADO", new BigDecimal("156000.00"), -15),
                crearPedido(4L, "PROCESANDO", new BigDecimal("78000.00"), -3),
                crearPedido(5L, "CANCELADO", new BigDecimal("92000.00"), -8),
                
                // Pedidos medianos (hace 1-3 meses)
                crearPedido(6L, "ENTREGADO", new BigDecimal("134000.00"), -45),
                crearPedido(7L, "ENTREGADO", new BigDecimal("198000.00"), -60),
                crearPedido(8L, "ENTREGADO", new BigDecimal("76500.00"), -75),
                crearPedido(9L, "ENTREGADO", new BigDecimal("112000.00"), -90),
                crearPedido(10L, "ENTREGADO", new BigDecimal("87500.00"), -105),
                
                // Pedidos antiguos (hace 3-6 meses)
                crearPedido(11L, "ENTREGADO", new BigDecimal("165000.00"), -120),
                crearPedido(12L, "ENTREGADO", new BigDecimal("145000.00"), -135),
                crearPedido(13L, "ENTREGADO", new BigDecimal("98000.00"), -150),
                crearPedido(14L, "ENTREGADO", new BigDecimal("187000.00"), -165),
                crearPedido(15L, "ENTREGADO", new BigDecimal("123000.00"), -180),
                
                // Pedidos históricos adicionales
                crearPedido(16L, "ENTREGADO", new BigDecimal("67000.00"), -200),
                crearPedido(17L, "ENTREGADO", new BigDecimal("234000.00"), -220),
                crearPedido(18L, "ENTREGADO", new BigDecimal("156000.00"), -240),
                crearPedido(19L, "ENTREGADO", new BigDecimal("89000.00"), -260),
                crearPedido(20L, "ENTREGADO", new BigDecimal("178000.00"), -280),
                
                // Pedidos de empresas colombianas
                crearPedido(21L, "ENTREGADO", new BigDecimal("450000.00"), -30),
                crearPedido(22L, "PROCESANDO", new BigDecimal("678000.00"), -2),
                crearPedido(23L, "ENVIADO", new BigDecimal("234000.00"), -7),
                crearPedido(24L, "ENTREGADO", new BigDecimal("345000.00"), -14),
                crearPedido(25L, "ENTREGADO", new BigDecimal("567000.00"), -21)
            );
            
            pedidoRepositorio.saveAll(pedidos);
            logger.info("Se crearon {} pedidos colombianos", pedidos.size());
            
        } catch (Exception e) {
            logger.error("Error al crear pedidos: {}", e.getMessage(), e);
        }
    }
    
    private Pedido crearPedido(Long clienteId, String estado, BigDecimal total, int diasAtras) {
        Pedido pedido = new Pedido();
        pedido.setClienteId(clienteId);
        pedido.setEstado(estado);
        pedido.setTotal(total);
        pedido.setFecha(OffsetDateTime.now().plusDays(diasAtras));
        return pedido;
    }
}