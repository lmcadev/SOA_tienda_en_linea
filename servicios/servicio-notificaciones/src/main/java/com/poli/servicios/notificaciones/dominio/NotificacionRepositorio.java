package com.poli.servicios.notificaciones.dominio;

import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificacionRepositorio extends JpaRepository<Notificacion, Long> {
}