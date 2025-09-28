package com.poli.servicios.pagos.config;

import com.poli.servicios.pagos.entidad.Pago;
import com.poli.servicios.pagos.dominio.PagoRepositorio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Inicializador de datos para pagos de pedidos colombianos.
 */
@Component
@Order(3)
public class InicializadorDatosPagos implements ApplicationRunner {
    
    private static final Logger logger = LoggerFactory.getLogger(InicializadorDatosPagos.class);
    
    private final PagoRepositorio pagoRepositorio;
    private final Random random = new Random();
    
    // Medios de pago populares en Colombia
    private final String[] mediosPago = {
        "PSE", "Tarjeta de Crédito", "Nequi", "Daviplata", "Bancolombia", 
        "Efecty", "Baloto", "Tarjeta Débito", "Transferencia Bancaria", "PayU"
    };
    
    public InicializadorDatosPagos(PagoRepositorio pagoRepositorio) {
        this.pagoRepositorio = pagoRepositorio;
    }
    
    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.info("Verificando inicialización de pagos colombianos...");
        long totalPagos = pagoRepositorio.count();
        logger.info("Total de pagos existentes: {}", totalPagos);
        
        if (totalPagos == 0) {
            logger.info("Inicializando pagos de pedidos colombianos...");
            crearPagosColombianos();
            logger.info("Pagos colombianos inicializados exitosamente");
        } else {
            logger.info("ℹYa existen pagos, omitiendo inicialización");
        }
    }
    
    private void crearPagosColombianos() {
        List<Pago> pagos = Arrays.asList(
            // Pagos para pedidos recientes
            crearPago(1L, "COMPLETADO", new BigDecimal("125000.00"), "PSE", -5),
            crearPago(2L, "COMPLETADO", new BigDecimal("89500.00"), "Nequi", -10),
            crearPago(3L, "COMPLETADO", new BigDecimal("67800.00"), "Tarjeta de Crédito", -15),
            crearPago(4L, "PENDIENTE", new BigDecimal("234500.00"), "Bancolombia", -3),
            crearPago(5L, "COMPLETADO", new BigDecimal("156700.00"), "Daviplata", -20),
            
            // Pagos de la semana pasada
            crearPago(6L, "COMPLETADO", new BigDecimal("78900.00"), "Efecty", -7),
            crearPago(7L, "COMPLETADO", new BigDecimal("145600.00"), "PSE", -12),
            crearPago(8L, "PROCESANDO", new BigDecimal("98700.00"), "Tarjeta Débito", -4),
            crearPago(9L, "COMPLETADO", new BigDecimal("203400.00"), "Transferencia Bancaria", -18),
            crearPago(10L, "CANCELADO", new BigDecimal("56800.00"), "Baloto", -8),
            
            // Pagos empresariales (montos más altos)
            crearPago(16L, "PROCESANDO", new BigDecimal("1250000.00"), "Transferencia Bancaria", -2),
            crearPago(17L, "COMPLETADO", new BigDecimal("890000.00"), "PSE", -6),
            crearPago(18L, "COMPLETADO", new BigDecimal("675000.00"), "Bancolombia", -14),
            crearPago(19L, "PENDIENTE", new BigDecimal("1100000.00"), "Transferencia Bancaria", -1),
            crearPago(20L, "COMPLETADO", new BigDecimal("445000.00"), "PSE", -9),
            
            // Más pagos individuales
            crearPago(11L, "COMPLETADO", new BigDecimal("87500.00"), "Nequi", -25),
            crearPago(12L, "PENDIENTE", new BigDecimal("134600.00"), "PayU", -1),
            crearPago(13L, "COMPLETADO", new BigDecimal("76400.00"), "Daviplata", -6),
            crearPago(14L, "COMPLETADO", new BigDecimal("298700.00"), "Tarjeta de Crédito", -11),
            crearPago(15L, "COMPLETADO", new BigDecimal("159800.00"), "PSE", -16),
            
            // Pagos históricos
            crearPago(21L, "COMPLETADO", new BigDecimal("95400.00"), "Efecty", -45),
            crearPago(22L, "COMPLETADO", new BigDecimal("123700.00"), "PSE", -60),
            crearPago(23L, "COMPLETADO", new BigDecimal("87600.00"), "Nequi", -75),
            crearPago(24L, "COMPLETADO", new BigDecimal("176500.00"), "Bancolombia", -50),
            crearPago(25L, "CANCELADO", new BigDecimal("67800.00"), "Baloto", -65)
        );
        
        pagoRepositorio.saveAll(pagos);
        logger.info("Se crearon {} pagos colombianos", pagos.size());
    }
    
    private Pago crearPago(Long pedidoId, String estado, BigDecimal monto, String medioPago, int diasAtras) {
        Pago pago = new Pago();
        pago.setPedidoId(pedidoId);
        pago.setEstado(estado);
        pago.setMonto(monto);
        pago.setMedioPago(medioPago);
        
        // Calcular fecha basada en días atrás con algo de variación aleatoria
        OffsetDateTime fechaPago = OffsetDateTime.now()
            .plusDays(diasAtras)
            .plusHours(random.nextInt(24))
            .plusMinutes(random.nextInt(60));
        pago.setFecha(fechaPago);
        
        return pago;
    }
}