package co.edu.unbosque.ridesmartv2.viajeModule.model.entity;

import co.edu.unbosque.ridesmartv2.estacionModule.model.entity.Estacion;
import co.edu.unbosque.ridesmartv2.pagoModule.model.entity.Pago;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "Viaje")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Viaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idViaje;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idReserva", referencedColumnName = "idReserva")
    private Reserva idReserva;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idEstacionFin")
    private Estacion idEstacionFin;

    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private int duracion;
    private double costo;
    @OneToOne(mappedBy = "idViaje")
    private Pago idPago;
}
