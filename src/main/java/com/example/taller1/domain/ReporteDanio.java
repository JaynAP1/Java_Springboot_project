package com.example.taller1.domain;

import jakarta.persistence.Column;
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
@Table(name = "reporte_danio")
@Setter
@Getter
public class ReporteDanio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "herramienta_id", nullable = false)
    private Herramienta herramienta;

    private String estado; // Ej: "Dañada", "En reparación", "Reparada"

    @Column(length = 500)
    private String descripcion;

    public ReporteDanio(Long id, Herramienta herramienta, String estado, String descripcion) {
        this.id = id;
        this.herramienta = herramienta;
        this.estado = estado;
        this.descripcion = descripcion;
    }

    
}
