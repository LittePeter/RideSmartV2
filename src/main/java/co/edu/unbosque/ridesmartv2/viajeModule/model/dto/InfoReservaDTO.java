package co.edu.unbosque.ridesmartv2.viajeModule.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * DTO auxiliar utilizado para crear una reserva con datos mínimos necesarios.
 * <p>
 * Contiene el ID de la estación, identificación del usuario y tipo de viaje.
 * </p>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InfoReservaDTO {

    private long estacion;
    private String usuario;
    private String tipoViaje;
}
