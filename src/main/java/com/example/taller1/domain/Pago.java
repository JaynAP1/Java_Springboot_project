package com.example.taller1.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "pagos")
@Setter
@Getter
public class Pago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "reserva_id")
    private Reserva reserva;

    private long monto;
    private LocalDateTime fechaPago = LocalDateTime.now();
    private String metodoPago;
    private String estadoPago = "PENDIENTE";


    public Pago(){
        
    }

    
    public Pago(Long id, Reserva reserva, long monto, LocalDateTime fechaPago, String metodoPago, String estadoPago) {
        this.id = id;
        this.reserva = reserva;
        this.monto = monto;
        this.fechaPago = fechaPago;
        this.metodoPago = metodoPago;
        this.estadoPago = estadoPago;
    }

    

}
