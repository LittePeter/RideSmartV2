package co.edu.unbosque.ridesmartv2.bicicletaModule.service;

import co.edu.unbosque.ridesmartv2.bicicletaModule.model.dto.BicicletaDTO;
import co.edu.unbosque.ridesmartv2.bicicletaModule.model.entity.Bicicleta;
import co.edu.unbosque.ridesmartv2.bicicletaModule.model.persistence.BicicletaRepository;
import org.modelmapper.ModelMapper;
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
        return biciRepository
                .findAll()
                .stream()
                .map(b -> mp.map(b, BicicletaDTO.class))
                .toList();
    }

    public BicicletaDTO obtenerBicicleta(long id) {
        return mp.map(biciRepository.findById(id), BicicletaDTO.class);
    }

    public List<BicicletaDTO> obtenerBicicletasPorEstado(String estado) {
        return biciRepository
                .findByEstado(estado)
                .stream()
                .map(b -> mp.map(b, BicicletaDTO.class))
                .toList();
    }

    public List<BicicletaDTO> obtenerBicicletasPorEstacion(long estacion) {
        return biciRepository
                .findByEstacion(estacion)
                .stream()
                .map(b -> mp.map(b, BicicletaDTO.class))
                .toList();
    }

    public void reubicarBicicleta(long idBicicleta, long estacion) {
        biciRepository.updateEstacionBici(idBicicleta, estacion);
    }

    public void bloquearBicicleta(long idBicicleta) {
        biciRepository.updateCandado(idBicicleta, "BLOQUEADO");
    }

    public void desbloquearBicicleta(long idBicicleta) {
        biciRepository.updateCandado(idBicicleta, "DESBLOQUEADO");
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
