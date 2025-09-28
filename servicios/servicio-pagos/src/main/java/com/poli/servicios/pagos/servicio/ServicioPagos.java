package com.poli.servicios.pagos.servicio;

import com.poli.servicios.pagos.api.dto.PagoDTO;
import com.poli.servicios.pagos.dominio.PagoRepositorio;
import com.poli.servicios.pagos.entidad.Pago;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.Optional;

@Service
public class ServicioPagos {
  private final PagoRepositorio repo;
  public ServicioPagos(PagoRepositorio repo) { this.repo = repo; }

  @Transactional
  public Pago crear(PagoDTO dto) {
    Pago p = new Pago();
    p.setPedidoId(dto.pedidoId);
    p.setMonto(dto.monto);
    p.setMedioPago(dto.medioPago);
    p.setEstado(dto.estado);
    p.setFecha(dto.fecha != null ? dto.fecha : OffsetDateTime.now());
    return repo.save(p);
  }

  public Page<Pago> listar(int pagina, int tamanio, Sort sort) {
    return repo.findAll(PageRequest.of(pagina, tamanio, sort));
  }

  public Pago obtener(Long id) {
    Optional<Pago> opt = repo.findById(id);
    return opt.orElseThrow(() -> new IllegalArgumentException("No existe el pago con id " + id));
  }

  @Transactional
  public Pago actualizar(Long id, PagoDTO dto) {
    Pago p = obtener(id);
    if (dto.pedidoId != null) p.setPedidoId(dto.pedidoId);
    if (dto.monto != null) p.setMonto(dto.monto);
    if (dto.medioPago != null) p.setMedioPago(dto.medioPago);
    if (dto.estado != null) p.setEstado(dto.estado);
    if (dto.fecha != null) p.setFecha(dto.fecha);
    return repo.save(p);
  }

  @Transactional
  public void eliminar(Long id) {
    repo.deleteById(id);
  }
}