package co.edu.unbosque.ridesmartv2.viajeModule.service;

import co.edu.unbosque.ridesmartv2.viajeModule.model.dto.ReservaDTO;

import java.util.List;

public interface InterfaceReservaService {

    boolean createReserva(ReservaDTO reservaDTO);
    List<ReservaDTO> getReservas();
    List<ReservaDTO> getReservasByUsuario(String idUsuario);
    List<ReservaDTO> getReservasByBicicleta(long idBicicleta);
    List<ReservaDTO> getReservasByEstacion(String idEstacion);
    ReservaDTO getReserva(long idReserva);
    boolean cancelarReserva(long idReserva);
    boolean cumplirReserva(long idReserva);
    boolean expirarReserva(long idReserva);
}
