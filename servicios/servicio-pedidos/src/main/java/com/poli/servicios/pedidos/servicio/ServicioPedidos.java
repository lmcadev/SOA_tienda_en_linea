package com.poli.servicios.pedidos.servicio;

import com.poli.servicios.pedidos.api.dto.PedidoDTO;
import com.poli.servicios.pedidos.dominio.Pedido;
import com.poli.servicios.pedidos.dominio.PedidoRepositorio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.Optional;

/**
 * Servicio de dominio para pedidos.
 */
@Service
public class ServicioPedidos {

  private final PedidoRepositorio repo;

  public ServicioPedidos(PedidoRepositorio repo) {
    this.repo = repo;
  }

  @Transactional
  public Pedido crear(PedidoDTO dto) {
    Pedido p = new Pedido();
    p.setClienteId(dto.clienteId);
    p.setFecha(dto.fecha != null ? dto.fecha : OffsetDateTime.now());
    p.setEstado(dto.estado);
    p.setTotal(dto.total);
    return repo.save(p);
  }

  public Page<Pedido> listar(int pagina, int tamanio, Sort sort) {
    return repo.findAll(PageRequest.of(pagina, tamanio, sort));
  }

  public Pedido obtener(Long id) {
    Optional<Pedido> opt = repo.findById(id);
    return opt.orElseThrow(() -> new IllegalArgumentException("No existe el pedido con id " + id));
  }

  @Transactional
  public Pedido actualizar(Long id, PedidoDTO dto) {
    Pedido p = obtener(id);
    if (dto.clienteId != null) p.setClienteId(dto.clienteId);
    if (dto.fecha != null) p.setFecha(dto.fecha);
    if (dto.estado != null) p.setEstado(dto.estado);
    if (dto.total != null) p.setTotal(dto.total);
    return repo.save(p);
  }

  @Transactional
  public void eliminar(Long id) {
    repo.deleteById(id);
  }
}