package co.edu.unbosque.ridesmartv2.estacionModule.model.dto;

import co.edu.unbosque.ridesmartv2.bicicletaModule.model.dto.BicicletaDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
/**
 * Objeto de transferencia de datos (DTO) para la entidad {@link Estacion}.
 * <p>
 * Se utiliza para exponer información de una estación a través de la API REST,
 * incluyendo una lista de bicicletas disponibles en formato DTO.
 * </p>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstacionDTO {

    private String idEstacion;
    private String nombre;
    private String categoria;
    private int capacidad;
    private double latitud;
    private double longitud;
    private String estado;
    private List<BicicletaDTO> biciList;
}
