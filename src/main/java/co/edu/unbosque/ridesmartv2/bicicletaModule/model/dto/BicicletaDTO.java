package co.edu.unbosque.ridesmartv2.bicicletaModule.model.dto;

import co.edu.unbosque.ridesmartv2.estacionModule.model.dto.EstacionDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BicicletaDTO {

    private Long idBicicleta;
    private String tipo;
    private String estado;
    private int bateria;
    private boolean candado;
    private EstacionDTO estacionDTO;
}