package com.example.taller1.infraestructure.controllers;

import com.example.taller1.domain.Usuario;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @PostMapping
    public String createUsuario(@RequestBody Usuario usuario) {
        return "Hola mundo";
    }
    @GetMapping
    public String getUsuarios() {
        return "Hola mundo";
    }
    @PostMapping("/login")
    public String login(@RequestBody Usuario usuario) {
        return "Hola mundo" + usuario.getUsername() + " " + usuario.getPassword();
    }
}
