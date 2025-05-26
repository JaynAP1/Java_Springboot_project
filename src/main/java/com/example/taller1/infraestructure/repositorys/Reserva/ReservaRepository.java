package com.example.taller1.infraestructure.repositorys.Reserva;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.taller1.domain.Reserva;

public interface ReservaRepository extends JpaRepository<Reserva,Long> {
    List<Reserva> findByfechaInicioBetweenOrderByIdAsc(LocalDateTime fechaInicio, LocalDateTime fechaFin);
    List<Reserva> findByEstado(String estado);
    List<Reserva> findByFechaInicioBetweenAndEstadoContainingIgnoreCase(LocalDateTime inicio, LocalDateTime fin, String estado);
    Reserva findById(int id);
}
