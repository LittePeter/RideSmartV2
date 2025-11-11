package co.edu.unbosque.ridesmartv2.viajeModule.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservaDTO {

    private long idReserva;
    private long usuario;
    private long estacion;
    private long bicicletaId;
    private String tipoViaje;
    private LocalDateTime fechaReserva;
    private LocalDateTime fechaVencimiento;
    private String estadoReserva;
}
