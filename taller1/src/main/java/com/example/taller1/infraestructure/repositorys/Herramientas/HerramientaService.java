package com.example.taller1.infraestructure.repositorys.Herramientas;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.taller1.domain.Herramienta;

@Service
public class HerramientaService {


    private final HerramientaRepository herramientaRepository;

    // Inyección del repositorio mediante constructor
    public HerramientaService(HerramientaRepository herramientaRepository) {
        this.herramientaRepository = herramientaRepository;
    }

    // Obtener todas las herramientas disponibles
    public List<Herramienta> obtenerTodasLasHerramientas() {
        return herramientaRepository.findByDisponibleTrue();
    }

    // Obtener herramientas por categoría
    public List<Herramienta> obtenerHerramientasPorCategoria(String categoria) {
        return herramientaRepository.findByCategoria(categoria);
    }


    public List<Herramienta> obtenerPorProveedor(Long idProveedor) {
        return herramientaRepository.findByProveedorId(idProveedor);
    }
    
    public Optional<Herramienta> actualizarHerramienta(Long id, Herramienta datos) {
    return herramientaRepository.findById(id).map(h -> {
        h.setNombre(datos.getNombre());
        h.setCantidad(datos.getCantidad());
        h.setDescripcion(datos.getDescripcion());
        h.setCategoria(datos.getCategoria());
        h.setPrecioDiario(datos.getPrecioDiario());
        h.setImagenUrl(datos.getImagenUrl());
        h.setDisponible(datos.isDisponible());

        // para cambiar proveedor también, verifica y asigna:
        if (datos.getProveedor() != null) {
            h.setProveedor(datos.getProveedor());
        }

        return herramientaRepository.save(h);
    });

   
}
    
}
