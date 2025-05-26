package com.example.taller1.auth;

import com.example.taller1.auth.dto.AuthRequest;
import com.example.taller1.auth.dto.AuthResponse;
import com.example.taller1.auth.dto.LoginRequest;
import com.example.taller1.domain.Role;
import com.example.taller1.domain.Usuario;
import com.example.taller1.infraestructure.repositorys.users.UsuarioRepository;
import com.example.taller1.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(AuthRequest request) {
        if (usuarioRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("El email ya está registrado");
        }

        Usuario usuario = new Usuario();
        usuario.setEmail(request.getEmail());
        usuario.setContrasena(passwordEncoder.encode(request.getPassword()));
        usuario.setNombre1(request.getNombre1());
        usuario.setApellido1(request.getApellido1());
        usuario.setRol(Role.USER);

        usuarioRepository.save(usuario);

        String token = jwtService.generateToken(usuario);

        return AuthResponse.builder()
                .token(token)
                .email(usuario.getEmail())
                .build();
    }

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        Usuario usuario = usuarioRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        String token = jwtService.generateToken(usuario);

        return AuthResponse.builder()
                .token(token)
                .email(usuario.getEmail())
                .tipo("Bearer")
                .nombre1(usuario.getNombre1())
                .apellido1(usuario.getApellido1())
                .role(usuario.getRol())
                .build();
    }
}

