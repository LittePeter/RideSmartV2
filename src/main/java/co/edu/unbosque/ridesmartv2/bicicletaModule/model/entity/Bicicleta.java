package co.edu.unbosque.ridesmartv2.bicicletaModule.model.entity;

import co.edu.unbosque.ridesmartv2.estacionModule.model.entity.Estacion;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table (name = "BICICLETA")
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
    @JoinColumn(name = "nombre")
    private Estacion estacion;
}
