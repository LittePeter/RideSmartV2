package co.edu.unbosque.ridesmartv2.viajeModule.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * DTO auxiliar utilizado para iniciar un viaje.
 * <p>
 * Contiene el ID de la reserva y el ID de la bicicleta asociada.
 * </p>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InfoInitViajeDTO {
    private long reserva;
    private long bicicleta;
}
