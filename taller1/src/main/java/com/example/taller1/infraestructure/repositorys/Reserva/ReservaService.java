package com.example.taller1.infraestructure.repositorys.Reserva;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.taller1.domain.Herramienta;
import com.example.taller1.domain.Reserva;
import com.example.taller1.domain.Usuario;
import com.example.taller1.infraestructure.repositorys.Herramientas.HerramientaRepository;
import com.example.taller1.infraestructure.repositorys.Usuario.UsuarioRepository;
@Service
public class ReservaService {
    private final ReservaRepository reservaRepository;
    private final UsuarioRepository usuarioRepository;
    private final HerramientaRepository herramientaRepository;

    

    public ReservaService(ReservaRepository reservaRepository,
            UsuarioRepository usuarioRepository,
            HerramientaRepository herramientaRepository) {
        this.reservaRepository = reservaRepository;
        this.usuarioRepository = usuarioRepository;
        this.herramientaRepository = herramientaRepository;
    }

    public Reserva agregarReserva(Long usuarioId, Long herramientaId,LocalDateTime fechaInicio,LocalDateTime fechaFin) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Herramienta herramienta = herramientaRepository.findById(herramientaId)
            .orElseThrow(() -> new RuntimeException("Herramienta no encontrada"));

        Reserva reserva = new Reserva();
        reserva.setCliente(usuario);
        reserva.setHerramienta(herramienta);
        reserva.setFechaInicio(fechaInicio);
        reserva.setFechaFin(fechaFin);
        return reservaRepository.save(reserva);
    }

    public Reserva actualizarReserva(Long reservaId, LocalDateTime fechaFin,String estado) {
        Reserva reserva = reservaRepository.findById(reservaId)
            .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));

        reserva.setEstado(estado);
        reserva.setFechaFin(fechaFin);

        return reservaRepository.save(reserva);
    }

    public List<Reserva> listarReservas(String fechaFin, String fechaInicio, String estado) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        boolean filtrarPorFecha = (fechaInicio != null && !fechaInicio.isEmpty()) && (fechaFin != null && !fechaFin.isEmpty());
        boolean filtrarPorEstado = estado != null && !estado.isEmpty();

        if (filtrarPorFecha && filtrarPorEstado) {
            return reservaRepository.findByFechaInicioBetweenAndEstadoContainingIgnoreCase(
                LocalDateTime.parse(fechaInicio, formatter),
                LocalDateTime.parse(fechaFin, formatter),
                estado
            );
        } else if (filtrarPorFecha) {
            return reservaRepository.findByfechaInicioBetweenOrderByIdAsc(
                LocalDateTime.parse(fechaInicio, formatter),
                LocalDateTime.parse(fechaFin, formatter)
            );
        } else if (filtrarPorEstado) {
            System.out.println(estado);
            return reservaRepository.findByEstado(estado);
        } else {
            return reservaRepository.findAll();
        } 
    }

     public Reserva obtenerReservaPorId(int id){
        return reservaRepository.findById(id);

    }
}
