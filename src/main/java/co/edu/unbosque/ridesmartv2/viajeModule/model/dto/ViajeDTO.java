package co.edu.unbosque.ridesmartv2.viajeModule.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViajeDTO {

    private long idViaje;
    private long usuario;
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
