package co.edu.unbosque.ridesmartv2.viajeModule.model.dto;

import co.edu.unbosque.ridesmartv2.bicicletaModule.model.dto.BicicletaDTO;
import co.edu.unbosque.ridesmartv2.estacionModule.model.dto.EstacionDTO;
import co.edu.unbosque.ridesmartv2.userModule.model.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservaDTO {

    private long idReserva;
    private UserDto idUsuario;
    private EstacionDTO idEstacion;
    private BicicletaDTO idBicicleta;
    private String tipoViaje;
    private LocalDateTime fechaReserva;
    private String estadoReserva;
}
