package com.example.taller1.domain;

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
@Table(name = "herramientas")
@Getter
@Setter
public class Herramienta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "proveedor_id")
    private Usuario proveedor;
    private long cantidad;
    private String nombre;
    private String descripcion;
    private String categoria;
    private long precioDiario;
    private boolean disponible = true;
    private String imagenUrl;

    public Herramienta() {
       
    }


    public Herramienta(Long id, Usuario proveedor,long cantidad, String nombre, String descripcion, String categoria,
            long precioDiario, boolean disponible, String imagenUrl) {
        this.id = id;
        this.proveedor = proveedor;
        this.cantidad= cantidad;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.precioDiario = precioDiario;
        this.disponible = disponible;
        this.imagenUrl = imagenUrl;
    }

    
}

