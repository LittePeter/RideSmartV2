package co.edu.unbosque.ridesmartv2.viajeModule.service;

import co.edu.unbosque.ridesmartv2.viajeModule.model.dto.InfoReservaDTO;
import co.edu.unbosque.ridesmartv2.viajeModule.model.dto.ReservaDTO;

import java.util.List;

public interface ReservaServiceI {
    ReservaDTO crearReserva(InfoReservaDTO info);
    void expirarReserva(long idReserva);
    void cancelarReserva(long idReserva);
    void cumplirReserva(long idReserva);
    ReservaDTO obtenerReserva(long idReserva);
    List<ReservaDTO> obtenerReservas();
}
