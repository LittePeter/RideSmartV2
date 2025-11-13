package co.edu.unbosque.ridesmartv2.estacionModule.service;

import co.edu.unbosque.ridesmartv2.mapper.ModelMapper;
import co.edu.unbosque.ridesmartv2.estacionModule.model.dto.EstacionDTO;
import co.edu.unbosque.ridesmartv2.estacionModule.model.entity.Estacion;
import co.edu.unbosque.ridesmartv2.estacionModule.model.persistence.EstacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstacionService {

    @Autowired
    private EstacionRepository estacionRepository;
    @Autowired
    private ModelMapper mp;

    public void crearEstacion(EstacionDTO estacionDTO) {
        Estacion estacion = mp.map(estacionDTO, Estacion.class);
        estacionRepository.save(estacion);
    }

    public EstacionDTO obtenerEstacion(String nombre) {
        EstacionDTO estacion = mp.map(estacionRepository.findByNombre(nombre), EstacionDTO.class);
        return estacion;
    }

    public List<EstacionDTO> obtenerEstacionPorCategoria(String Categoria) {
        List<EstacionDTO> estaciones = mp.mapList(estacionRepository.findByCategoria(Categoria), EstacionDTO.class);
        return estaciones;
    }

    public List<EstacionDTO> obtenerEstacionPorEstado(String estado) {
        List<EstacionDTO> estaciones = mp.mapList(estacionRepository.findByEstado(estado), EstacionDTO.class);
        return estaciones;
    }

    public void habilitarEstacion(String idEstacion) {
        estacionRepository.updateEstado(idEstacion, "DISPONIBLE");
    }

    public void desHabilitarEstacion(String idEstacion) {
        estacionRepository.updateEstado(idEstacion, "NO DISPONIBLE");
    }

    public void generarOrdenRedistribucion (String idEstacion) {
        EstacionDTO estacion = obtenerEstacion(idEstacion);
        if (estacion != null) {
            if (estacion.getBiciList().isEmpty()){
                //Logica para crear una orden de Redistribución.
            }
        }
    }
}
