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
@Table(name = "reservas")
@Getter
@Setter
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Usuario cliente;

    @ManyToOne
    @JoinColumn(name = "herramienta_id")
    private Herramienta herramienta;
    
    private LocalDateTime fechaInicio = LocalDateTime.now();
    private LocalDateTime fechaFin = LocalDateTime.now();
    private String estado = "pendiente";
    private Integer cantidad;
    
    public Reserva(Long id, Usuario cliente, Herramienta herramienta, LocalDateTime fechaInicio, LocalDateTime fechaFin,
            String estado, Integer cantidad) {
        this.id = id;
        this.cliente = cliente;
        this.herramienta = herramienta;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estado = estado;
        this.cantidad = cantidad;
    }
    public Reserva(){};
}
