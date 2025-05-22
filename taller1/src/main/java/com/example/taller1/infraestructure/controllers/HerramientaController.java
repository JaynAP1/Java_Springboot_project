package com.example.taller1.infraestructure.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.taller1.domain.Herramienta;
import com.example.taller1.infraestructure.repositorys.Herramientas.HerramientaRepository;
import com.example.taller1.infraestructure.repositorys.Herramientas.HerramientaService;


@RestController
@RequestMapping("/api/herramientas")
@CrossOrigin(origins = "http://localhost:5173")
public class HerramientaController {

    private final HerramientaService herramientaService;
    private final HerramientaRepository herramientaRepository;
    
    public HerramientaController(HerramientaService herramientaService, HerramientaRepository herramientaRepository) {
        this.herramientaService = herramientaService;
        this.herramientaRepository = herramientaRepository;
    }




    @GetMapping("/{id}")
    public ResponseEntity<Herramienta> obtenerHerramienta(@PathVariable Long id) {
        return herramientaRepository.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }


    @GetMapping("/proveedor/{idProveedor}")
    public List<Herramienta> obtenerPorProveedor(@PathVariable Long idProveedor) {
        return herramientaService.obtenerPorProveedor(idProveedor);
    }
    
    @PostMapping
    public ResponseEntity<Herramienta> addHerramienta(@RequestBody Herramienta herramienta) {
        if (herramienta.getNombre() == null || herramienta.getNombre().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        Herramienta saved = herramientaRepository.save(herramienta);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Herramienta> actualizarHerramienta(@PathVariable Long id, @RequestBody Herramienta nuevaData) {
        return herramientaRepository.findById(id).map(h -> {
            h.setNombre(nuevaData.getNombre());
            h.setCantidad(nuevaData.getCantidad());
            h.setDescripcion(nuevaData.getDescripcion());
            h.setCategoria(nuevaData.getCategoria());
            h.setPrecioDiario(nuevaData.getPrecioDiario());
            h.setImagenUrl(nuevaData.getImagenUrl());
            h.setProveedor(nuevaData.getProveedor());
            return ResponseEntity.ok(herramientaRepository.save(h));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarHerramienta(@PathVariable Long id) {
        if (!herramientaRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        herramientaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }


    // GET /api/herramientas
    @GetMapping
    public List<Herramienta> listarHerramientas(@RequestParam(required = false) String categoria) {
        if (categoria != null && !categoria.isEmpty()) {
            return herramientaService.obtenerHerramientasPorCategoria(categoria);
        }
        return herramientaService.obtenerTodasLasHerramientas();
    }
    
    
}
