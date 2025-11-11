package co.edu.unbosque.ridesmartv2.bicicletaModule.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BicicletaDTO {

    private Long idBicicleta;
    private int bateria;
    private String candado;
    private String estado;
    private long estacion;
}
