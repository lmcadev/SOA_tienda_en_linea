package com.poli.servicios.pedidos.config;

import com.poli.servicios.pedidos.entidad.Pedido;
import com.poli.servicios.pedidos.dominio.PedidoRepositorio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Inicializador de datos para pedidos de clientes colombianos.
 */
@Component
public class InicializadorDatosPedidos implements CommandLineRunner {
    
    private static final Logger logger = LoggerFactory.getLogger(InicializadorDatosPedidos.class);
    
    private final PedidoRepositorio pedidoRepositorio;
    private final Random random = new Random();
    
    public InicializadorDatosPedidos(PedidoRepositorio pedidoRepositorio) {
        this.pedidoRepositorio = pedidoRepositorio;
    }
    
    @Override
    public void run(String... args) throws Exception {
        if (pedidoRepositorio.count() == 0) {
            logger.info("Inicializando pedidos de clientes colombianos...");
            crearPedidosColombianos();
            logger.info("Pedidos inicializados exitosamente");
        } else {
            logger.info("Ya existen pedidos, omitiendo inicialización");
        }
    }
    
    private void crearPedidosColombianos() {
        List<Pedido> pedidos = Arrays.asList(
            // Pedidos recientes (últimos 30 días)
            crearPedido(1L, "PROCESANDO", new BigDecimal("125000.00"), -5),
            crearPedido(2L, "ENVIADO", new BigDecimal("89500.00"), -10),
            crearPedido(3L, "ENTREGADO", new BigDecimal("67800.00"), -15),
            crearPedido(4L, "PROCESANDO", new BigDecimal("234500.00"), -3),
            crearPedido(5L, "ENTREGADO", new BigDecimal("156700.00"), -20),
            
            // Pedidos de la semana pasada
            crearPedido(6L, "ENVIADO", new BigDecimal("78900.00"), -7),
            crearPedido(7L, "ENTREGADO", new BigDecimal("145600.00"), -12),
            crearPedido(8L, "PROCESANDO", new BigDecimal("98700.00"), -4),
            crearPedido(9L, "ENTREGADO", new BigDecimal("203400.00"), -18),
            crearPedido(10L, "CANCELADO", new BigDecimal("56800.00"), -8),
            
            // Pedidos empresariales (montos más altos)
            crearPedido(16L, "PROCESANDO", new BigDecimal("1250000.00"), -2), // Tienda Paisa
            crearPedido(17L, "ENVIADO", new BigDecimal("890000.00"), -6), // Café Colombia
            crearPedido(18L, "ENTREGADO", new BigDecimal("675000.00"), -14), // Artesanías Wayuu
            crearPedido(19L, "PROCESANDO", new BigDecimal("1100000.00"), -1), // Frutas Urabá
            crearPedido(20L, "ENTREGADO", new BigDecimal("445000.00"), -9), // Dulces Santander
            
            // Más pedidos individuales
            crearPedido(11L, "ENTREGADO", new BigDecimal("87500.00"), -25),
            crearPedido(12L, "PROCESANDO", new BigDecimal("134600.00"), -1),
            crearPedido(13L, "ENVIADO", new BigDecimal("76400.00"), -6),
            crearPedido(14L, "ENTREGADO", new BigDecimal("298700.00"), -11),
            crearPedido(15L, "ENTREGADO", new BigDecimal("159800.00"), -16),
            
            // Pedidos históricos (últimos 3 meses)
            crearPedido(1L, "ENTREGADO", new BigDecimal("95400.00"), -45),
            crearPedido(3L, "ENTREGADO", new BigDecimal("123700.00"), -60),
            crearPedido(5L, "ENTREGADO", new BigDecimal("87600.00"), -75),
            crearPedido(7L, "ENTREGADO", new BigDecimal("176500.00"), -50),
            crearPedido(9L, "CANCELADO", new BigDecimal("67800.00"), -65)
        );
        
        pedidoRepositorio.saveAll(pedidos);
        logger.info("Se crearon {} pedidos colombianos", pedidos.size());
    }
    
    private Pedido crearPedido(Long clienteId, String estado, BigDecimal total, int diasAtras) {
        Pedido pedido = new Pedido();
        pedido.setClienteId(clienteId);
        pedido.setEstado(estado);
        pedido.setTotal(total);
        
        // Calcular fecha basada en días atrás con algo de variación aleatoria
        OffsetDateTime fechaPedido = OffsetDateTime.now()
            .plusDays(diasAtras)
            .plusHours(random.nextInt(24))
            .plusMinutes(random.nextInt(60));
        pedido.setFecha(fechaPedido);
        
        return pedido;
    }
}