package com.example.taller1.infraestructure.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.http.MediaType;

import com.example.taller1.domain.Reserva;
import com.example.taller1.infraestructure.repositorys.Reserva.ReservaService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping(value = "/reservas", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "http://localhost:5173")
public class ReservaController {

    private final ReservaService reservaService;

    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @GetMapping("/id_reserva/")
    public Reserva obtenerReservaPorId(@RequestParam int id) {
        return reservaService.obtenerReservaPorId(id);
    }

    @GetMapping("/")
    public List<Reserva> listaReservas(@RequestParam(required = false) String fechaInicio,
            @RequestParam(required = false) String fechaFin,
            @RequestParam(required = false) String estado) {
        List<Reserva> results = reservaService.listarReservas(fechaFin,
                fechaInicio,
                estado);
        return results;
    }

    @PostMapping("/crear")
    public Reserva crearReserva(@RequestParam Long usuarioId,
            @RequestParam Long herramientaId,
            @RequestParam String fechaInicio,
            @RequestParam String fechaFin,
            @RequestParam Integer cantidad) {
        LocalDateTime fecha = LocalDateTime.parse(fechaInicio, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
        LocalDateTime fechaF = LocalDateTime.parse(fechaInicio, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
        return reservaService.agregarReserva(usuarioId, herramientaId, fecha, fechaF, cantidad);
    }

    @PutMapping("/actualizar")
    public Reserva actualizarReserva(@RequestParam Long reservaId,
            @RequestParam String fechaFin,
            @RequestParam String estado,
            @RequestParam Integer cantidad) {
        LocalDateTime fecha = LocalDateTime.parse(fechaFin, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
        return reservaService.actualizarReserva(reservaId, fecha, estado,cantidad);
    }

    @GetMapping("/usuarioId")
    public List<Reserva> mostrarReservaPorUsuario(@RequestParam int id){
        return reservaService.listarPorUsuario(id);
    };

        @GetMapping("/por_proveedor")
    public List<Reserva> obtenerReservasPorProveedor(@RequestParam int id) {
        return reservaService.listarPorProveedor(id);
    }

}
