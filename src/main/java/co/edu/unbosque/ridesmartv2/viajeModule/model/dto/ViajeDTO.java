package co.edu.unbosque.ridesmartv2.viajeModule.model.dto;

import co.edu.unbosque.ridesmartv2.estacionModule.model.dto.EstacionDTO;
import co.edu.unbosque.ridesmartv2.pagoModule.model.dto.PagoDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViajeDTO {

    private long idViaje;
    private ReservaDTO idReserva;
    private EstacionDTO idEstacionFin;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private int duracion;
    private double costo;
    private PagoDTO idPago;
}
