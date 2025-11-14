package co.edu.unbosque.ridesmartv2.viajeModule.service;

import co.edu.unbosque.ridesmartv2.viajeModule.model.dto.ViajeDTO;
import co.edu.unbosque.ridesmartv2.viajeModule.model.entity.Viaje;

import java.util.List;

public interface InterfaceViajeService {

    boolean iniciarViaje(long idReserva);
    ViajeDTO obtenerViaje(long idViaje);
    ViajeDTO obtenerViajePorReserva(long idReserva);
    List<ViajeDTO> obtenerViajePorEstacionFin(String idEstacion);
    List<ViajeDTO> obtenerViajePorTipoViaje(String tipoViaje);
    boolean finalizarViaje(long idViaje, String idEstacionFin);
    Viaje getEntityById(long idViaje);
}
