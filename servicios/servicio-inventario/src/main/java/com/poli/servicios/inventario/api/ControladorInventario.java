package com.poli.servicios.inventario.api;

import com.poli.servicios.inventario.api.dto.ProductoInventarioDTO;
import com.poli.servicios.inventario.dominio.ProductoInventario;
import com.poli.servicios.inventario.servicio.ServicioInventario;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Inventario", description = "Gestión del inventario de productos")
@RestController
@RequestMapping("/inventario")
public class ControladorInventario {

  private final ServicioInventario servicio;

  public ControladorInventario(ServicioInventario servicio) {
    this.servicio = servicio;
  }

  @Operation(summary = "Crear producto", description = "Crea un nuevo producto en el inventario")
  @PostMapping
  public ResponseEntity<ProductoInventario> crear(@RequestBody ProductoInventarioDTO dto) {
    return ResponseEntity.ok(servicio.crear(dto));
  }

  @Operation(summary = "Listar productos", description = "Lista productos con paginación y orden")
  @GetMapping
  public ResponseEntity<Page<ProductoInventario>> listar(
      @RequestParam(defaultValue = "0") int pagina,
      @RequestParam(defaultValue = "20") int tamanio,
      @RequestParam(defaultValue = "id,ASC") String orden) {
    String[] partes = orden.split(",");
    Sort sort = Sort.by(Sort.Direction.fromString(partes[1]), partes[0]);
    return ResponseEntity.ok(servicio.listar(pagina, tamanio, sort));
  }

  @Operation(summary = "Obtener producto por id", description = "Devuelve un producto por su identificador")
  @GetMapping("/{id}")
  public ResponseEntity<ProductoInventario> obtener(@PathVariable Long id) {
    return ResponseEntity.ok(servicio.obtener(id));
  }

  @Operation(summary = "Actualizar producto", description = "Actualiza los datos de un producto existente")
  @PutMapping("/{id}")
  public ResponseEntity<ProductoInventario> actualizar(@PathVariable Long id, @RequestBody ProductoInventarioDTO dto) {
    return ResponseEntity.ok(servicio.actualizar(id, dto));
  }

  @Operation(summary = "Eliminar producto", description = "Elimina un producto del inventario por id")
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> eliminar(@PathVariable Long id) {
    servicio.eliminar(id);
    return ResponseEntity.noContent().build();
  }
}