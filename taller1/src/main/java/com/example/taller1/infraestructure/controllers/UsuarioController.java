package com.example.taller1.infraestructure.controllers;

import com.example.taller1.domain.Usuario;
import com.example.taller1.infraestructure.repositorys.users.UsuarioRepository;
import com.example.taller1.infraestructure.repositorys.users.UsuarioService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "http://localhost:5173")
public class UsuarioController {
    UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/email")
    public Usuario UsuarioPorId(@RequestParam String email) {
        return usuarioService.UsuarioPorId(email);
    }
    
}
