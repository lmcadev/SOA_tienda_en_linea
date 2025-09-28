package com.poli.servicios.pagos.dominio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poli.servicios.pagos.entidad.Pago;

public interface PagoRepositorio extends JpaRepository<Pago, Long> {
}