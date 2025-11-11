package co.edu.unbosque.ridesmartv2.viajeModule.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "RESERVA")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idReserva;

    private String usuario;
    private long estacion;
    private long bicicletaId;
    private String tipoViaje;
    private LocalDateTime fechaReserva;
    private LocalDateTime fechaVencimiento;
    private String estadoReserva;
}