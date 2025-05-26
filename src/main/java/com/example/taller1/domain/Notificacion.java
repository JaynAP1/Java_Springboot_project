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
@Table(name = "notificaciones")
@Getter
@Setter
public class Notificacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    private String mensaje;
    private boolean leido = false;
    private LocalDateTime fechaCreacion = LocalDateTime.now();
    
    public Notificacion(Long id, Usuario usuario, String mensaje, boolean leido, LocalDateTime fechaCreacion) {
        this.id = id;
        this.usuario = usuario;
        this.mensaje = mensaje;
        this.leido = leido;
        this.fechaCreacion = fechaCreacion;
    }
    
}
