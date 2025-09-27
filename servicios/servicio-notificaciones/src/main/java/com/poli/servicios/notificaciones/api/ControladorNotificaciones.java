package com.poli.servicios.notificaciones.api;

import com.poli.servicios.notificaciones.api.dto.NotificacionDTO;
import com.poli.servicios.notificaciones.dominio.Notificacion;
import com.poli.servicios.notificaciones.servicio.ServicioNotificaciones;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Notificaciones", description = "Gestión de notificaciones")
@RestController
@RequestMapping("/notificaciones")
public class ControladorNotificaciones {
  private final ServicioNotificaciones servicio;
  public ControladorNotificaciones(ServicioNotificaciones servicio) { this.servicio = servicio; }

  @Operation(summary = "Crear notificación", description = "Crea una nueva notificación")
  @PostMapping
  public ResponseEntity<Notificacion> crear(@RequestBody NotificacionDTO dto) {
    return ResponseEntity.ok(servicio.crear(dto));
  }

  @Operation(summary = "Listar notificaciones", description = "Lista notificaciones con paginación y orden")
  @GetMapping
  public ResponseEntity<Page<Notificacion>> listar(
      @RequestParam(defaultValue = "0") int pagina,
      @RequestParam(defaultValue = "20") int tamanio,
      @RequestParam(defaultValue = "id,ASC") String orden) {
    String[] partes = orden.split(",");
    Sort sort = Sort.by(Sort.Direction.fromString(partes[1]), partes[0]);
    return ResponseEntity.ok(servicio.listar(pagina, tamanio, sort));
  }

  @Operation(summary = "Obtener notificación", description = "Obtiene una notificación por id")
  @GetMapping("/{id}")
  public ResponseEntity<Notificacion> obtener(@PathVariable Long id) {
    return ResponseEntity.ok(servicio.obtener(id));
  }

  @Operation(summary = "Actualizar notificación", description = "Actualiza una notificación existente")
  @PutMapping("/{id}")
  public ResponseEntity<Notificacion> actualizar(@PathVariable Long id, @RequestBody NotificacionDTO dto) {
    return ResponseEntity.ok(servicio.actualizar(id, dto));
  }

  @Operation(summary = "Eliminar notificación", description = "Elimina una notificación por id")
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> eliminar(@PathVariable Long id) {
    servicio.eliminar(id);
    return ResponseEntity.noContent().build();
  }
}