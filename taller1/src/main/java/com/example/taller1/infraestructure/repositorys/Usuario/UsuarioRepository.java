package com.example.taller1.infraestructure.repositorys.Usuario;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.taller1.domain.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario,Long>{
    List<Usuario> findById(int id);
}
