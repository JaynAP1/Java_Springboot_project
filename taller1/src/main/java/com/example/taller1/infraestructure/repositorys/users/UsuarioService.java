package com.example.taller1.infraestructure.repositorys.users;

import org.springframework.stereotype.Service;

import com.example.taller1.domain.Usuario;

@Service
public class UsuarioService {
    
    UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    } 


    public Usuario UsuarioPorId(String email) {
        Usuario nuevUsuario=usuarioRepository.findByEmail(email)
        .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return  nuevUsuario;
    }
}
