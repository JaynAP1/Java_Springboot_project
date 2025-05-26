package com.example.taller1.infraestructure.controllers;

import com.example.taller1.domain.Role;
import com.example.taller1.domain.Usuario;
import com.example.taller1.domain.dto.UsuarioBasicoDTO;
import com.example.taller1.infraestructure.repositorys.users.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * Busca un usuario por su nombre
     * @param nombre Nombre del usuario a buscar
     * @return Datos completos del usuario si existe, o respuesta 404 si no existe
     */
    @GetMapping({"/usuarios/exists/{nombre}", "/exists/{nombre}"})
    public ResponseEntity<Usuario> existsByNombre(@PathVariable String nombre) {
        try {
            return usuarioRepository.findByNombre1(nombre)
                .map(usuario -> {
                    // Create a copy to avoid exposing sensitive information
                    Usuario safeUsuario = new Usuario();
                    safeUsuario.setId(usuario.getId());
                    safeUsuario.setNombre1(usuario.getNombre1());
                    safeUsuario.setNombre2(usuario.getNombre2());
                    safeUsuario.setApellido1(usuario.getApellido1());
                    safeUsuario.setApellido2(usuario.getApellido2());
                    safeUsuario.setEmail(usuario.getEmail());
                    safeUsuario.setRol(usuario.getRol());
                    safeUsuario.setFechaRegistro(usuario.getFechaRegistro());
                    return ResponseEntity.ok(safeUsuario);
                })
                .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * Actualiza la información básica de un usuario existente
     * @param usuarioDTO Información básica del usuario
     * @return El usuario actualizado sin información sensible
     */
    @PostMapping({"/usuarios/save-info"})
    public ResponseEntity<?> saveUserInfo(@RequestBody UsuarioBasicoDTO usuarioDTO) {
        try {
            // Buscar usuario existente por email
            return usuarioRepository.findByEmail(usuarioDTO.getEmail())
                .map(usuario -> {
                    // Actualizar información del usuario existente
                    usuario.setNombre1(usuarioDTO.getNombre1());
                    usuario.setNombre2(usuarioDTO.getNombre2());
                    usuario.setApellido1(usuarioDTO.getApellido1());
                    usuario.setApellido2(usuarioDTO.getApellido2());
                    
                    if (usuarioDTO.getRol() != null) {
                        usuario.setRol(usuarioDTO.getRol());
                    }
                    
                    Usuario savedUsuario = usuarioRepository.save(usuario);
            
                    // Create a safe copy without sensitive information
                    Usuario safeUsuario = new Usuario();
                    safeUsuario.setId(savedUsuario.getId());
                    safeUsuario.setNombre1(savedUsuario.getNombre1());
                    safeUsuario.setNombre2(savedUsuario.getNombre2());
                    safeUsuario.setApellido1(savedUsuario.getApellido1());
                    safeUsuario.setApellido2(savedUsuario.getApellido2());
                    safeUsuario.setEmail(savedUsuario.getEmail());
                    safeUsuario.setRol(savedUsuario.getRol());
                    safeUsuario.setFechaRegistro(savedUsuario.getFechaRegistro());
            
                    return ResponseEntity.ok(safeUsuario);
                })
                .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error al actualizar usuario: " + e.getMessage());
        }
    }
}
