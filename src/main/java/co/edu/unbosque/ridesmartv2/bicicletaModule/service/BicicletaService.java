package co.edu.unbosque.ridesmartv2.bicicletaModule.service;

import co.edu.unbosque.ridesmartv2.bicicletaModule.model.dto.BicicletaDTO;
import co.edu.unbosque.ridesmartv2.bicicletaModule.model.entity.Bicicleta;
import co.edu.unbosque.ridesmartv2.bicicletaModule.model.persistence.BicicletaRepository;
import co.edu.unbosque.ridesmartv2.config.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementación del servicio de gestión de bicicletas.
 * <p>
 * Coordina la lógica de negocio para crear, consultar y actualizar bicicletas,
 * y delega las operaciones de persistencia al repositorio.
 * </p>
 */
@Service
public class BicicletaService implements InterfaceBiciService {

    @Autowired
    private BicicletaRepository biciRepository;
    @Autowired
    private ModelMapper mp;

    @Override
    public void crearBicicleta(BicicletaDTO biciDTO) {
        Bicicleta bicicleta = mp.map(biciDTO, Bicicleta.class);
        biciRepository.save(bicicleta);
    }

    @Override
    public List<BicicletaDTO> obtenerBicicletas() {
        List<BicicletaDTO> bicicletas = mp.mapList(biciRepository.findAll(), BicicletaDTO.class);
        return bicicletas;
    }

    @Override
    public BicicletaDTO obtenerBicicleta(long id) {
        return mp.map(biciRepository.findById(id), BicicletaDTO.class);
    }

    @Override
    public List<BicicletaDTO> obtenerBicicletasPorEstado(String estado) {
        return mp.mapList(biciRepository.findByEstado(estado), BicicletaDTO.class);
    }

    @Override
    public List<BicicletaDTO> obtenerBicicletasPorEstacion(String estacion) {
        return mp.mapList(biciRepository.findByEstacion(estacion), BicicletaDTO.class);
    }

    @Override
    public void reubicarBicicleta(long idBicicleta, String estacion) {
        biciRepository.updateEstacionBici(idBicicleta, estacion);
    }

    @Override
    public void bloquearBicicleta(long idBicicleta) {
        biciRepository.updateCandado(idBicicleta, true);
    }

    @Override
    public void desbloquearBicicleta(long idBicicleta) {
        biciRepository.updateCandado(idBicicleta, false);
    }

    @Override
    public void habilitarBicicleta(long idBicicleta) {
        biciRepository.updateEstadoBici(idBicicleta, "DISPONIBLE");
    }

    @Override
    public void inhabilitarBicicleta(long idBicicleta) {
        biciRepository.updateEstadoBici(idBicicleta, "NO DISPONIBLE");
    }

    @Override
    public void usarBicicleta(long idBicicleta) {
        biciRepository.updateEstadoBici(idBicicleta, "EN USO");
    }

    @Override
    public void recargarBicicleta(long idBicicleta, int newBateria) {
        biciRepository.updateBateria(idBicicleta, newBateria);
    }
}
