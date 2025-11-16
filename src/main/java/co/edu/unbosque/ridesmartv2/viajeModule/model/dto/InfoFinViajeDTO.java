package co.edu.unbosque.ridesmartv2.viajeModule.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * DTO auxiliar utilizado para finalizar un viaje.
 * <p>
 * Contiene el ID del viaje y el ID de la estación de destino.
 * </p>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InfoFinViajeDTO {
    private Long viajeId;
    private Long estacionFin;
}
