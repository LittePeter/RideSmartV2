package co.edu.unbosque.ridesmartv2.bicicletaModule.model.entity;

import co.edu.unbosque.ridesmartv2.estacionModule.model.entity.Estacion;
import co.edu.unbosque.ridesmartv2.viajeModule.model.entity.Reserva;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idEstacion")
    private Estacion idEstacion;

    @OneToMany(mappedBy = "idBicicleta")
    private List<Reserva> reserva;
}
