package co.edu.unbosque.ridesmartv2.viajeModule.model.dto;

import co.edu.unbosque.ridesmartv2.estacionModule.model.dto.EstacionDTO;
import co.edu.unbosque.ridesmartv2.pagoModule.model.dto.PagoDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
/**
 * Objeto de transferencia de datos (DTO) para la entidad Viaje.
 * <p>
 * Se utiliza para exponer información de un viaje a través de la API REST,
 * sin exponer directamente la entidad ni sus relaciones JPA.
 * </p>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViajeDTO {

    private long idViaje;
    private ReservaDTO idReserva;
    private EstacionDTO idEstacionFin;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private String tipoViaje;
    private int duracion;
    private double costo;
    private PagoDTO idPago;
}
