package com.example.taller1.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.example.taller1.domain.Role;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
    private String token;
    private String tipo = "Bearer";
    private String email;
    private String nombre1;
    private String apellido1;
    private Role role;
}

