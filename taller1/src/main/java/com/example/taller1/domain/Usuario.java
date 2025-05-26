package com.example.taller1.domain;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre1;
    private String nombre2;
    private String apellido1;
    private String apellido2;
    private String email;
    private String contrasena;
    
    @Enumerated(EnumType.STRING)
    private Role rol;
    
    private LocalDateTime fechaRegistro = LocalDateTime.now();
    private boolean accountNonExpired = true;
    private boolean accountNonLocked = true;
    private boolean credentialsNonExpired = true;
    private boolean enabled = true;

    
    public Usuario(Long id, String nombre1, String nombre2, String apellido1, String apellido2, 
                  String email, String contrasena, Role rol, LocalDateTime fechaRegistro) {
        this.id = id;
        this.nombre1 = nombre1;
        this.nombre2 = nombre2;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.email = email;
        this.contrasena = contrasena;
        this.rol = rol;
        this.fechaRegistro = fechaRegistro;
    }

    public Usuario() {
    }
    
    public Usuario(String nombre1, String contrasena) {
        this.nombre1 = nombre1;
        this.contrasena = contrasena;
    }

    public String getNombreCompleto() {
        return nombre1 + " " + nombre2 + " " + apellido1 + " " + apellido2;
    }
    
    @Override
    public String getUsername() {
        // Using email as the unique username for authentication
        return email;
    }

    @Override
    public String getPassword() {
        return contrasena;
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + rol.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
