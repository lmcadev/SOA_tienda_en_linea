package com.poli.servicios.pedidos.api;

import com.poli.servicios.pedidos.api.dto.PedidoDTO;
import com.poli.servicios.pedidos.dominio.Pedido;
import com.poli.servicios.pedidos.servicio.ServicioPedidos;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Pedidos", description = "Gestión de pedidos")
@RestController
@RequestMapping("/pedidos")
public class ControladorPedidos {
  private final ServicioPedidos servicio;
  public ControladorPedidos(ServicioPedidos servicio) { this.servicio = servicio; }

  @Operation(summary = "Crear pedido", description = "Crea un nuevo pedido")
  @PostMapping
  public ResponseEntity<Pedido> crear(@RequestBody PedidoDTO dto) {
    return ResponseEntity.ok(servicio.crear(dto));
  }

  @Operation(summary = "Listar pedidos", description = "Lista pedidos con paginación y orden")
  @GetMapping
  public ResponseEntity<Page<Pedido>> listar(
      @RequestParam(defaultValue = "0") int pagina,
      @RequestParam(defaultValue = "20") int tamanio,
      @RequestParam(defaultValue = "id,ASC") String orden) {
    String[] partes = orden.split(",");
    Sort sort = Sort.by(Sort.Direction.fromString(partes[1]), partes[0]);
    return ResponseEntity.ok(servicio.listar(pagina, tamanio, sort));
  }

  @Operation(summary = "Obtener pedido", description = "Obtiene un pedido por su id")
  @GetMapping("/{id}")
  public ResponseEntity<Pedido> obtener(@PathVariable Long id) {
    return ResponseEntity.ok(servicio.obtener(id));
  }

  @Operation(summary = "Actualizar pedido", description = "Actualiza un pedido existente")
  @PutMapping("/{id}")
  public ResponseEntity<Pedido> actualizar(@PathVariable Long id, @RequestBody PedidoDTO dto) {
    return ResponseEntity.ok(servicio.actualizar(id, dto));
  }

  @Operation(summary = "Eliminar pedido", description = "Elimina un pedido por id")
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> eliminar(@PathVariable Long id) {
    servicio.eliminar(id);
    return ResponseEntity.noContent().build();
  }
}