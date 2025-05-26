package com.example.taller1.infraestructure.repositorys.Reserva;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.taller1.domain.Reserva;

public interface ReservaRepository extends JpaRepository<Reserva,Long> {
    List<Reserva> findByfechaInicioBetweenOrderByIdAsc(LocalDateTime fechaInicio, LocalDateTime fechaFin);
    List<Reserva> findByEstadoOrderByIdAsc(String estado);
    List<Reserva> findByFechaInicioBetweenAndEstadoContainingIgnoreCaseOrderByIdAsc(LocalDateTime inicio, LocalDateTime fin, String estado);
    List<Reserva> findByClienteId(int id);
    @Query("SELECT r FROM Reserva r WHERE r.herramienta.proveedor.id = :proveedorId ORDER BY r.id ASC")
    List<Reserva> findByProveedorId(@Param("proveedorId") int proveedorId);
    Reserva findById(int id);
}
