package co.edu.unbosque.ridesmartv2.bicicletaModule.service;

import co.edu.unbosque.ridesmartv2.bicicletaModule.model.dto.BicicletaDTO;
import co.edu.unbosque.ridesmartv2.bicicletaModule.model.entity.Bicicleta;
import co.edu.unbosque.ridesmartv2.bicicletaModule.model.persistence.BicicletaRepository;
import co.edu.unbosque.ridesmartv2.mapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BicicletaService {

    @Autowired
    private BicicletaRepository biciRepository;
    @Autowired
    private ModelMapper mp;

    public void crearBicicleta(BicicletaDTO biciDTO) {
        Bicicleta bicicleta = mp.map(biciDTO, Bicicleta.class);
        biciRepository.save(bicicleta);
    }

    public List<BicicletaDTO> obtenerBicicletas() {
        List<BicicletaDTO> bicicletas = mp.mapList(biciRepository.findAll(), BicicletaDTO.class);
        return bicicletas;
    }

    public BicicletaDTO obtenerBicicleta(long id) {
        return mp.map(biciRepository.findById(id), BicicletaDTO.class);
    }

    public List<BicicletaDTO> obtenerBicicletasPorEstado(String estado) {
        return mp.mapList(biciRepository.findByEstado(estado), BicicletaDTO.class);
    }

    public List<BicicletaDTO> obtenerBicicletasPorEstacion(String estacion) {
        return mp.mapList(biciRepository.findByEstacion(estacion), BicicletaDTO.class);
    }

    public void reubicarBicicleta(long idBicicleta, String estacion) {
        biciRepository.updateEstacionBici(idBicicleta, estacion);
    }

    public void bloquearBicicleta(long idBicicleta) {
        biciRepository.updateCandado(idBicicleta, true);
    }

    public void desbloquearBicicleta(long idBicicleta) {
        biciRepository.updateCandado(idBicicleta, false);
    }

    public void habilitarBicicleta(long idBicicleta) {
        biciRepository.updateEstadoBici(idBicicleta, "DISPONIBLE");
    }

    public void inhabilitarBicicleta(long idBicicleta) {
        biciRepository.updateEstadoBici(idBicicleta, "NO DISPONIBLE");
    }

    public void usarBicicleta(long idBicicleta) {
        biciRepository.updateEstadoBici(idBicicleta, "EN USO");
    }

    public void recargarBicicleta(long idBicicleta, int newBateria) {
        biciRepository.updateBateria(idBicicleta, newBateria);
    }
}
