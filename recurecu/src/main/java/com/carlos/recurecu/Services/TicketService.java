package com.carlos.recurecu.Services;

import com.carlos.recurecu.Datos.BeanTicket;
import com.carlos.recurecu.Datos.BeanUsuario;
import com.carlos.recurecu.Datos.PrioridadDTO;
import com.carlos.recurecu.Datos.TicketDTO;
import com.carlos.recurecu.Repository.TicketRepository;
import com.carlos.recurecu.Repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TicketService {

    private final TicketRepository ticketRepositorio;
    private final UsuarioRepository usuarioRepositorio;

    public TicketService(TicketRepository ticketRepositorio,
                         UsuarioRepository usuarioRepositorio) {
        this.ticketRepositorio = ticketRepositorio;
        this.usuarioRepositorio = usuarioRepositorio;
    }

    public BeanTicket crearTicket(TicketDTO dto) {
        BeanUsuario u = usuarioRepositorio.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!u.isActivo())
            throw new RuntimeException("El usuario no está activo");

        if (dto.getDescripcion() == null || dto.getDescripcion().isBlank())
            throw new RuntimeException("La descripción no puede estar vacía");

        BeanTicket t = new BeanTicket();
        t.setUsuario(u);
        t.setDescripcion(dto.getDescripcion());
        t.setEstado(BeanTicket.Estado.ABIERTO);
        t.setPrioridad(BeanTicket.Prioridad.valueOf(dto.getPrioridad()));
        return ticketRepositorio.save(t);
    }

    public BeanTicket actualizarEstado(Long ticketId) {
        BeanTicket t = ticketRepositorio.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket no encontrado"));

        if (t.getEstado() == BeanTicket.Estado.CERRADO)
            throw new RuntimeException("El ticket ya está cerrado, no se puede modificar");

        if (t.getEstado() == BeanTicket.Estado.ABIERTO)
            t.setEstado(BeanTicket.Estado.EN_PROCESO);
        else if (t.getEstado() == BeanTicket.Estado.EN_PROCESO)
            t.setEstado(BeanTicket.Estado.CERRADO);

        return ticketRepositorio.save(t);
    }

    public BeanTicket reasignarPrioridad(Long ticketId, PrioridadDTO dto) {
        BeanTicket t = ticketRepositorio.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket no encontrado"));

        if (t.getEstado() == BeanTicket.Estado.CERRADO)
            throw new RuntimeException("No se puede cambiar prioridad de un ticket cerrado");

        t.setPrioridad(BeanTicket.Prioridad.valueOf(dto.getNuevaPrioridad()));
        return ticketRepositorio.save(t);
    }

    public List<BeanTicket> obtenerTicketsUsuario(Long usuarioId) {
        return ticketRepositorio.findByUsuarioId(usuarioId);
    }
}