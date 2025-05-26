package com.example.taller1.infraestructure.repositorys.users;

import com.example.taller1.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
    boolean existsByEmail(String email);
    boolean existsByNombre1(String nombre1);
    Optional<Usuario> findByNombre1(String nombre1);
}

