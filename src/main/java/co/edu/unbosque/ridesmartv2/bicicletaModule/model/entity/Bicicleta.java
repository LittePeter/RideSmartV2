package co.edu.unbosque.ridesmartv2.bicicletaModule.model.entity;

import co.edu.unbosque.ridesmartv2.estacionModule.model.entity.Estacion;
import co.edu.unbosque.ridesmartv2.viajeModule.model.entity.Reserva;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
/**
 * Representa una bicicleta del sistema RideSmart.
 * <p>
 * Cada bicicleta tiene un estado, nivel de batería, candado y está asociada a una estación.
 * Está relacionada con reservas a través de una relación {@code @OneToMany}.
 * </p>
 */
@Entity
@Table (name = "Bicicleta")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bicicleta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBicicleta;

    private String tipo;
    private String estado;
    private int bateria;
    private boolean candado;
    private String estacion;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idEstacion")
    private Estacion idEstacion;

    @OneToMany(mappedBy = "idBicicleta")
    private List<Reserva> reserva;
}
