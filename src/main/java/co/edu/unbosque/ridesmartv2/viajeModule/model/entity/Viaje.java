package co.edu.unbosque.ridesmartv2.viajeModule.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "VIAJE")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Viaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idViaje;

    private String usuario;
    private long bicicleta;
    private String tipoViaje;
    private long estacionInicio;
    private long estacionFin;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private int duracion;
    private double precio;
    private String estado;
    private long pago;
}
