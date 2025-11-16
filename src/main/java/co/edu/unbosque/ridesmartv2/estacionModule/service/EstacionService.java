package co.edu.unbosque.ridesmartv2.estacionModule.service;

import co.edu.unbosque.ridesmartv2.config.ModelMapper;
import co.edu.unbosque.ridesmartv2.estacionModule.model.dto.EstacionDTO;
import co.edu.unbosque.ridesmartv2.estacionModule.model.entity.Estacion;
import co.edu.unbosque.ridesmartv2.estacionModule.model.persistence.EstacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * Implementación del servicio de gestión de estaciones.
 * <p>
 * Coordina la lógica de negocio para crear, consultar y actualizar estaciones.
 * Incluye una lógica básica para la generación de órdenes de redistribución
 * cuando una estación no tiene bicicletas.
 * </p>
 */
@Service
public class EstacionService implements InterfaceEstacionService {

    @Autowired
    private EstacionRepository estacionRepository;
    @Autowired
    private ModelMapper mp;

    @Override
    public void crearEstacion(EstacionDTO estacionDTO) {
        Estacion estacion = mp.map(estacionDTO, Estacion.class);
        estacionRepository.save(estacion);
    }

    @Override
    public EstacionDTO obtenerEstacion(String nombre) {
        EstacionDTO estacion = mp.map(estacionRepository.findByNombre(nombre), EstacionDTO.class);
        return estacion;
    }

    @Override
    public List<EstacionDTO> obtenerEstacionPorCategoria(String Categoria) {
        List<EstacionDTO> estaciones = mp.mapList(estacionRepository.findByCategoria(Categoria), EstacionDTO.class);
        return estaciones;
    }

    @Override
    public List<EstacionDTO> obtenerEstacionPorEstado(String estado) {
        List<EstacionDTO> estaciones = mp.mapList(estacionRepository.findByEstado(estado), EstacionDTO.class);
        return estaciones;
    }

    @Override
    public void habilitarEstacion(String idEstacion) {
        estacionRepository.updateEstado(idEstacion, "DISPONIBLE");
    }

    @Override
    public void desHabilitarEstacion(String idEstacion) {
        estacionRepository.updateEstado(idEstacion, "NO DISPONIBLE");
    }

    @Override
    public void generarOrdenRedistribucion (String idEstacion) {
        EstacionDTO estacion = obtenerEstacion(idEstacion);
        if (estacion != null) {
            if (estacion.getBiciList().isEmpty()){
                //Logica para crear una orden de Redistribución.
            }
        }
    }
}
