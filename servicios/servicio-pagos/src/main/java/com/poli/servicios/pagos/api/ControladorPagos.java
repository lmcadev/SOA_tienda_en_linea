package com.poli.servicios.pagos.api;

import com.poli.servicios.pagos.api.dto.PagoDTO;
import com.poli.servicios.pagos.entidad.Pago;
import com.poli.servicios.pagos.servicio.ServicioPagos;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Pagos", description = "Gestión de pagos")
@RestController
@RequestMapping("/pagos")
public class ControladorPagos {
  private final ServicioPagos servicio;
  public ControladorPagos(ServicioPagos servicio) { this.servicio = servicio; }

  @Operation(summary = "Crear pago", description = "Registra un pago nuevo")
  @PostMapping
  public ResponseEntity<Pago> crear(@RequestBody PagoDTO dto) {
    return ResponseEntity.ok(servicio.crear(dto));
  }

  @Operation(summary = "Listar pagos", description = "Lista pagos con paginación y orden")
  @GetMapping
  public ResponseEntity<Page<Pago>> listar(
      @RequestParam(defaultValue = "0") int pagina,
      @RequestParam(defaultValue = "20") int tamanio,
      @RequestParam(defaultValue = "id,ASC") String orden) {
    String[] partes = orden.split(",");
    Sort sort = Sort.by(Sort.Direction.fromString(partes[1]), partes[0]);
    return ResponseEntity.ok(servicio.listar(pagina, tamanio, sort));
  }

  @Operation(summary = "Obtener pago", description = "Obtiene un pago por id")
  @GetMapping("/{id}")
  public ResponseEntity<Pago> obtener(@PathVariable Long id) {
    return ResponseEntity.ok(servicio.obtener(id));
  }

  @Operation(summary = "Actualizar pago", description = "Actualiza un pago")
  @PutMapping("/{id}")
  public ResponseEntity<Pago> actualizar(@PathVariable Long id, @RequestBody PagoDTO dto) {
    return ResponseEntity.ok(servicio.actualizar(id, dto));
  }

  @Operation(summary = "Eliminar pago", description = "Elimina un pago")
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> eliminar(@PathVariable Long id) {
    servicio.eliminar(id);
    return ResponseEntity.noContent().build();
  }
}