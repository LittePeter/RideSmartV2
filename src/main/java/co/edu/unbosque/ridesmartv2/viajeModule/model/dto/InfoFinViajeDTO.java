package co.edu.unbosque.ridesmartv2.viajeModule.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InfoFinViajeDTO {
    private Long viajeId;
    private Long estacionFin;
}
