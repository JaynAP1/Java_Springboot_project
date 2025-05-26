package com.example.taller1.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.example.taller1.domain.Role;

/**
 * DTO for handling basic user information without sensitive data like passwords
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioBasicoDTO {
    private String nombre1;
    private String nombre2;
    private String apellido1;
    private String apellido2;
    private String email;
    private Role rol;
}

