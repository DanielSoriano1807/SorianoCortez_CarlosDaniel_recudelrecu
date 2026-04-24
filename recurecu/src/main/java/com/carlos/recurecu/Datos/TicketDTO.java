package com.carlos.recurecu.Datos;

import lombok.Data;

@Data
public class TicketDTO {
    private Long usuarioId;
    private String descripcion;
    private String prioridad;
}