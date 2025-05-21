package com.example.taller1.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String email;
    private String contraseña;
    private String rol;
    private LocalDateTime fechaRegistro = LocalDateTime.now();
    
    public Usuario() {
    }

    public Usuario(Long id, String nombre, String apellido1, String apellido2, String email, String contraseña,
            String rol, LocalDateTime fechaRegistro) {
        this.id = id;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.email = email;
        this.contraseña = contraseña;
        this.rol = rol;
        this.fechaRegistro = fechaRegistro;
    }

    
   

    
}