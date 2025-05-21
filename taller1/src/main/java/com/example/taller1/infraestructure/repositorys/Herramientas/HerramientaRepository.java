package com.example.taller1.infraestructure.repositorys.Herramientas;

import java.util.List;

import com.example.taller1.domain.Herramienta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HerramientaRepository extends JpaRepository<Herramienta, Long> {

    List<Herramienta> findAll();

    List<Herramienta> findByDisponibleTrue();

    List<Herramienta> findByProveedorId(Long idProveedor);

    // Método para obtener herramientas por categoría
    List<Herramienta> findByCategoria(String categoria);
    
}
