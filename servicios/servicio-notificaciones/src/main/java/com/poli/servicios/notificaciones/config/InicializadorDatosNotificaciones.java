package com.poli.servicios.notificaciones.config;

import com.poli.servicios.notificaciones.entidad.Notificacion;
import com.poli.servicios.notificaciones.dominio.NotificacionRepositorio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Inicializador de datos para notificaciones de clientes colombianos.
 */
@Component
@Order(4)
public class InicializadorDatosNotificaciones implements ApplicationRunner {
    
    private static final Logger logger = LoggerFactory.getLogger(InicializadorDatosNotificaciones.class);
    
    private final NotificacionRepositorio notificacionRepositorio;
    private final Random random = new Random();
    
    // Usuarios colombianos para las notificaciones
    private final String[] usuariosColombianos = {
        "maria.gonzalez@email.com", "carlos.rodriguez@email.com", "ana.martinez@email.com",
        "jose.ramirez@email.com", "lucia.perez@email.com", "david.lopez@email.com",
        "sofia.hernandez@email.com", "miguel.torres@email.com", "valentina.castro@email.com",
        "tienda.paisa@empresa.co", "cafe.colombia@empresa.co", "artesanias.wayuu@empresa.co"
    };
    
    public InicializadorDatosNotificaciones(NotificacionRepositorio notificacionRepositorio) {
        this.notificacionRepositorio = notificacionRepositorio;
    }
    
    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.info("Verificando inicialización de notificaciones colombianas...");
        long totalNotificaciones = notificacionRepositorio.count();
        logger.info("Total de notificaciones existentes: {}", totalNotificaciones);
        
        if (totalNotificaciones == 0) {
            logger.info("Inicializando notificaciones de clientes...");
            crearNotificacionesColombianas();
            logger.info("Notificaciones colombianas inicializadas exitosamente");
        } else {
            logger.info("ℹYa existen notificaciones, omitiendo inicialización");
        }
    }
    
    private void crearNotificacionesColombianas() {
        List<Notificacion> notificaciones = Arrays.asList(
            // Notificaciones de pedidos recientes
            crearNotificacion("EMAIL", "maria.gonzalez@email.com", 
                "Su pedido #12345 de café Juan Valdez ha sido confirmado. Total: $125,000 COP", "ENVIADO", -1),
            crearNotificacion("SMS", "carlos.rodriguez@email.com", 
                "¡Excelente! Su pedido de plátanos del Urabá está en camino. Llegará mañana.", "ENTREGADO", -2),
            crearNotificacion("EMAIL", "ana.martinez@email.com", 
                "Gracias por su compra de artesanías wayuu. Su pedido #12347 ha sido entregado.", "ENTREGADO", -5),
                
            // Notificaciones promocionales
            crearNotificacion("EMAIL", "jose.ramirez@email.com", 
                "¡Oferta especial! 20% descuento en todos los productos de Santander. Código: SANTANDER20", "ENVIADO", -3),
            crearNotificacion("SMS", "lucia.perez@email.com", 
                "Nueva llegada: Aguacates Hass frescos de Antioquia. ¡Ordene ya!", "ENTREGADO", -4),
            crearNotificacion("EMAIL", "david.lopez@email.com", 
                "Festival del Café Colombiano: Descuentos hasta 30% en cafés premium", "ENVIADO", -2),
                
            // Notificaciones de pago
            crearNotificacion("EMAIL", "sofia.hernandez@email.com", 
                "Pago confirmado vía PSE por $89,500 COP. Su pedido será procesado pronto.", "ENTREGADO", -6),
            crearNotificacion("SMS", "miguel.torres@email.com", 
                "Pago recibido por Nequi. Gracias por elegir productos colombianos.", "ENTREGADO", -7),
                
            // Notificaciones empresariales
            crearNotificacion("EMAIL", "tienda.paisa@empresa.co", 
                "Pedido mayorista #EMP001 confirmado. 500kg de café procesándose para envío.", "ENVIADO", -1),
            crearNotificacion("EMAIL", "cafe.colombia@empresa.co", 
                "Su orden de exportación está lista. Documentos enviados para despacho internacional.", "ENTREGADO", -3),
            crearNotificacion("EMAIL", "artesanias.wayuu@empresa.co", 
                "Lote de 100 mochilas wayuu entregado exitosamente en Bogotá.", "ENTREGADO", -8),
                
            // Notificaciones de seguimiento
            crearNotificacion("SMS", "valentina.castro@email.com", 
                "Su pedido salió de Cartagena y llegará a Bogotá en 24 horas.", "ENTREGADO", -2),
            crearNotificacion("EMAIL", "alejandro.morales@email.com", 
                "Recordatorio: Su pedido de dulces santandereanos está listo para recoger.", "ENVIADO", -1),
                
            // Notificaciones de bienvenida
            crearNotificacion("EMAIL", "camila.vargas@email.com", 
                "¡Bienvenida a nuestra tienda online de productos colombianos! Disfrute 15% descuento en su primera compra.", "ENTREGADO", -10),
            crearNotificacion("SMS", "sebastian.jimenez@email.com", 
                "Gracias por registrarse. Explore lo mejor de Colombia desde Pasto.", "ENTREGADO", -12),
                
            // Notificaciones de restock
            crearNotificacion("EMAIL", "isabella.ruiz@email.com", 
                "¡Buenas noticias! El bocadillo veleño ya está disponible nuevamente.", "ENVIADO", -3),
            crearNotificacion("SMS", "mateo.gutierrez@email.com", 
                "Los chorizos santarrosanos están de vuelta en stock. ¡Ordene ya!", "ENTREGADO", -4),
                
            // Notificaciones de satisfacción
            crearNotificacion("EMAIL", "gabriela.sanchez@email.com", 
                "¿Qué tal su experiencia con nuestros productos boyacenses? Califíquenos aquí.", "ENVIADO", -15),
                
            // Notificaciones históricas
            crearNotificacion("EMAIL", "maria.gonzalez@email.com", 
                "Su pedido anterior de arepas paisas fue entregado. ¡Esperamos que lo haya disfrutado!", "ENTREGADO", -30),
            crearNotificacion("SMS", "carlos.rodriguez@email.com", 
                "Recordatorio: Próximas ofertas del Día de la Independencia en productos patrios.", "ENTREGADO", -20)
        );
        
        notificacionRepositorio.saveAll(notificaciones);
        logger.info("Se crearon {} notificaciones", notificaciones.size());
    }
    
    private Notificacion crearNotificacion(String tipo, String destinatario, String mensaje, String estado, int diasAtras) {
        Notificacion notificacion = new Notificacion();
        notificacion.setTipo(tipo);
        notificacion.setDestinatario(destinatario);
        notificacion.setMensaje(mensaje);
        notificacion.setEstadoEntrega(estado);
        
        // Calcular fecha basada en días atrás con algo de variación aleatoria
        OffsetDateTime fechaNotificacion = OffsetDateTime.now()
            .plusDays(diasAtras)
            .plusHours(random.nextInt(24))
            .plusMinutes(random.nextInt(60));
        notificacion.setFecha(fechaNotificacion);
        
        return notificacion;
    }
}