package co.edu.unbosque.ridesmartv2.viajeModule.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InfoReservaDTO {

    private long estacion;
    private String usuario;
    private String tipoViaje;
}
