package com.carlos.recurecu.Datos;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tickets")
public class BeanTicket {

    public enum Estado {
        ABIERTO, EN_PROCESO, CERRADO }
    public enum Prioridad {
        BAJA, MEDIA, ALTA }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descripcion;

    @Enumerated(EnumType.STRING)
    private Estado estado;

    @Enumerated(EnumType.STRING)
    private Prioridad prioridad;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private BeanUsuario usuario;
}