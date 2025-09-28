package com.poli.servicios.notificaciones.dominio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poli.servicios.notificaciones.entidad.Notificacion;

public interface NotificacionRepositorio extends JpaRepository<Notificacion, Long> {
}