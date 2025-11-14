package co.edu.unbosque.ridesmartv2.bicicletaModule.service;

import co.edu.unbosque.ridesmartv2.bicicletaModule.model.dto.BicicletaDTO;

import java.util.List;

public interface InterfaceBiciService {

    void crearBicicleta(BicicletaDTO biciDTO);
    List<BicicletaDTO> obtenerBicicletas();
    BicicletaDTO obtenerBicicleta(long id);
    List<BicicletaDTO> obtenerBicicletasPorEstado(String estado);
    List<BicicletaDTO> obtenerBicicletasPorEstacion(String estacion);
    void reubicarBicicleta(long idBicicleta, String estacion);
    void bloquearBicicleta(long idBicicleta);
    void desbloquearBicicleta(long idBicicleta);
    void habilitarBicicleta(long idBicicleta);
    void inhabilitarBicicleta(long idBicicleta);
    void usarBicicleta(long idBicicleta);
    void recargarBicicleta(long idBicicleta, int newBateria);
}
