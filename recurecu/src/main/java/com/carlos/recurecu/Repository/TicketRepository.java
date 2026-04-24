package com.carlos.recurecu.Repository;

import com.carlos.recurecu.Datos.BeanTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TicketRepository extends JpaRepository<BeanTicket, Long> {
    List<BeanTicket> findByUsuarioId(Long usuarioId);
}