package co.edu.unbosque.ridesmartv2.estacionModule.model.dto;

import co.edu.unbosque.ridesmartv2.bicicletaModule.model.dto.BicicletaDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
