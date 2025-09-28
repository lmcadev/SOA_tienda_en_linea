package com.poli.servicios.notificaciones.servicio;

import com.poli.servicios.notificaciones.api.dto.NotificacionDTO;
import com.poli.servicios.notificaciones.dominio.NotificacionRepositorio;
import com.poli.servicios.notificaciones.entidad.Notificacion;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.Optional;

@Service
public class ServicioNotificaciones {
  private final NotificacionRepositorio repo;
  public ServicioNotificaciones(NotificacionRepositorio repo) { this.repo = repo; }

  @Transactional
  public Notificacion crear(NotificacionDTO dto) {
    Notificacion n = new Notificacion();
    n.setTipo(dto.tipo);
    n.setDestinatario(dto.destinatario);
    n.setMensaje(dto.mensaje);
    n.setEstadoEntrega(dto.estadoEntrega);
    n.setFecha(dto.fecha != null ? dto.fecha : OffsetDateTime.now());
    return repo.save(n);
  }

  public Page<Notificacion> listar(int pagina, int tamanio, Sort sort) {
    return repo.findAll(PageRequest.of(pagina, tamanio, sort));
  }

  public Notificacion obtener(Long id) {
    Optional<Notificacion> opt = repo.findById(id);
    return opt.orElseThrow(() -> new IllegalArgumentException("No existe la notificación con id " + id));
  }

  @Transactional
  public Notificacion actualizar(Long id, NotificacionDTO dto) {
    Notificacion n = obtener(id);
    if (dto.tipo != null) n.setTipo(dto.tipo);
    if (dto.destinatario != null) n.setDestinatario(dto.destinatario);
    if (dto.mensaje != null) n.setMensaje(dto.mensaje);
    if (dto.estadoEntrega != null) n.setEstadoEntrega(dto.estadoEntrega);
    if (dto.fecha != null) n.setFecha(dto.fecha);
    return repo.save(n);
  }

  @Transactional
  public void eliminar(Long id) {
    repo.deleteById(id);
  }
}