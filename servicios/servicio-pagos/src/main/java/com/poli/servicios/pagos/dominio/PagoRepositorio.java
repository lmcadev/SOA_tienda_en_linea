package com.poli.servicios.pagos.dominio;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PagoRepositorio extends JpaRepository<Pago, Long> {
}