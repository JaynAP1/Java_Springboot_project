package com.example.taller1.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "devoluciones")
@Getter
@Setter
public class Devolucion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "reserva_id")
    private Reserva reserva;

    private LocalDateTime fechaDevolucion;
    private String estadoHerramienta;
    private String observaciones;

    public Devolucion() {
    }
    
    public Devolucion(Long id, Reserva reserva, LocalDateTime fechaDevolucion, String estadoHerramienta,
            String observaciones) {
        this.id = id;
        this.reserva = reserva;
        this.fechaDevolucion = fechaDevolucion;
        this.estadoHerramienta = estadoHerramienta;
        this.observaciones = observaciones;
    }
    
}
