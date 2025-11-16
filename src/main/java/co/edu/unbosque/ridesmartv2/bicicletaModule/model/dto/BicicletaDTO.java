package co.edu.unbosque.ridesmartv2.bicicletaModule.model.dto;

import co.edu.unbosque.ridesmartv2.estacionModule.model.dto.EstacionDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * Objeto de transferencia de datos (DTO) para la entidad Bicicleta
 * <p>
 * Se utiliza para exponer información de una bicicleta a través de la API REST,
 * incluyendo la estación a la que pertenece en formato DTO.
 * </p>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BicicletaDTO {

    private Long idBicicleta;
    private String tipo;
    private String estado;
    private int bateria;
    private boolean candado;
    private EstacionDTO idEstacion;
}