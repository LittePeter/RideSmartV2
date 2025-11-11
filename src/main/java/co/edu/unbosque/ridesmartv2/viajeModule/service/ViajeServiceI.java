package co.edu.unbosque.ridesmartv2.viajeModule.service;

import co.edu.unbosque.ridesmartv2.viajeModule.model.dto.InfoFinViajeDTO;
import co.edu.unbosque.ridesmartv2.viajeModule.model.dto.InfoInitViajeDTO;
import co.edu.unbosque.ridesmartv2.viajeModule.model.dto.ViajeDTO;

import java.util.List;

public interface ViajeServiceI {
    ViajeDTO iniciarViaje(InfoInitViajeDTO info);

    void finalizarViaje(InfoFinViajeDTO info);

    ViajeDTO obtenerViaje(long viajeId);

    List<ViajeDTO> obtenerViajes();
}
