package co.edu.unbosque.ridesmartv2.estacionModule.service;

import co.edu.unbosque.ridesmartv2.estacionModule.model.dto.EstacionDTO;

import java.util.List;

public interface InterfaceEstacionService {

    void crearEstacion(EstacionDTO estacionDTO);
    EstacionDTO obtenerEstacion(String nombre);
    List<EstacionDTO> obtenerEstacionPorCategoria(String Categoria);
    List<EstacionDTO> obtenerEstacionPorEstado(String estado);
    void habilitarEstacion(String idEstacion);
    void desHabilitarEstacion(String idEstacion);
    void generarOrdenRedistribucion (String idEstacion);
}
