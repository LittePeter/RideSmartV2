package co.edu.unbosque.ridesmartv2.viajeModule.model.entity;

import co.edu.unbosque.ridesmartv2.bicicletaModule.model.entity.Bicicleta;
import co.edu.unbosque.ridesmartv2.estacionModule.model.entity.Estacion;
import co.edu.unbosque.ridesmartv2.userModule.model.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
/**
 * Representa una reserva de bicicleta realizada por un usuario en una estación específica.
 * <p>
 * Una reserva puede tener diferentes estados: CONFIRMADA, CANCELADA, CUMPLIDA o EXPIRADA.
 * Está vinculada a un {@link User}, una {@link Estacion}, una {@link Bicicleta} y opcionalmente a un {@link Viaje}.
 * </p>
 */
@Entity
@Table(name = "Reserva")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idReserva;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUsuario")
    private User idUsuario;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idEstacion")
    private Estacion idEstacion;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idBicicleta")
    private Bicicleta idBicicleta;
    private String tipoViaje;
    private LocalDateTime fechaReserva;
    private String estadoReserva;

    @OneToOne(mappedBy = "idReserva")
    private Viaje viaje;
}