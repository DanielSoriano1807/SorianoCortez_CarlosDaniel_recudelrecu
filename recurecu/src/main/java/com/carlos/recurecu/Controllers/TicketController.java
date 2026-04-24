package com.carlos.recurecu.Controllers;

import com.carlos.recurecu.Datos.BeanTicket;
import com.carlos.recurecu.Datos.PrioridadDTO;
import com.carlos.recurecu.Datos.TicketDTO;
import com.carlos.recurecu.Services.TicketService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    private final TicketService service;

    public TicketController(TicketService service) {
        this.service = service;
    }

    @PostMapping
    public BeanTicket crear(@RequestBody TicketDTO dto) {
        return service.crearTicket(dto);
    }

    @PutMapping("/{id}/estado")
    public BeanTicket actualizarEstado(@PathVariable Long id) {
        return service.actualizarEstado(id);
    }

    @PutMapping("/{id}/prioridad")
    public BeanTicket reasignarPrioridad(@PathVariable Long id,
                                         @RequestBody PrioridadDTO dto) {
        return service.reasignarPrioridad(id, dto);
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<BeanTicket> ticketsUsuario(@PathVariable Long usuarioId) {
        return service.obtenerTicketsUsuario(usuarioId);
    }
}