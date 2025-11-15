package co.edu.unbosque.ridesmartv2.bicicletaModule.service;

import co.edu.unbosque.ridesmartv2.bicicletaModule.model.dto.BicicletaDTO;
import co.edu.unbosque.ridesmartv2.bicicletaModule.model.entity.Bicicleta;
import co.edu.unbosque.ridesmartv2.bicicletaModule.model.persistence.BicicletaRepository;
import co.edu.unbosque.ridesmartv2.config.ModelMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BicicletaServiceTest {

    @Mock
    private BicicletaRepository biciRepository;

    @Mock
    private ModelMapper mp;

    @InjectMocks
    private BicicletaService bicicletaService;


    @Test
    void crearBicicleta_debeGuardarLaEntidad() {
        BicicletaDTO dto = new BicicletaDTO();
        Bicicleta entity = new Bicicleta();

        when(mp.map(dto, Bicicleta.class)).thenReturn(entity);

        bicicletaService.crearBicicleta(dto);

        verify(mp).map(dto, Bicicleta.class);
        verify(biciRepository).save(entity);
    }


    @Test
    void obtenerBicicletas_debeRetornarListaDeDTO() {
        Bicicleta bici = new Bicicleta();
        BicicletaDTO dto = new BicicletaDTO();

        when(biciRepository.findAll()).thenReturn(List.of(bici));
        when(mp.mapList(List.of(bici), BicicletaDTO.class)).thenReturn(List.of(dto));

        List<BicicletaDTO> result = bicicletaService.obtenerBicicletas();

        assertEquals(1, result.size());
        verify(biciRepository).findAll();
    }


    @Test
    void obtenerBicicleta_debeRetornarDTO() {
        Bicicleta bici = new Bicicleta();
        BicicletaDTO dto = new BicicletaDTO();
        Optional<Bicicleta> optBici = Optional.of(bici);

        when(bici = biciRepository.findById(1L));
        when(mp.map(optBici, BicicletaDTO.class)).thenReturn(dto);

        // Act
        BicicletaDTO result = bicicletaService.obtenerBicicleta(1L);

        // Assert
        assertNotNull(result);
        assertEquals(dto, result);
        verify(biciRepository).findById(1L);
        verify(mp).map(optBici, BicicletaDTO.class);
    }


    @Test
    void obtenerBicicletasPorEstado_debeRetornarListaDTO() {
        Bicicleta bici = new Bicicleta();
        BicicletaDTO dto = new BicicletaDTO();

        when(biciRepository.findByEstado("DISPONIBLE")).thenReturn(List.of(bici));
        when(mp.mapList(List.of(bici), BicicletaDTO.class)).thenReturn(List.of(dto));

        List<BicicletaDTO> result = bicicletaService.obtenerBicicletasPorEstado("DISPONIBLE");

        assertEquals(1, result.size());
        verify(biciRepository).findByEstado("DISPONIBLE");
    }

    @Test
    void obtenerBicicletasPorEstacion_debeRetornarListaDTO() {
        Bicicleta bici = new Bicicleta();
        BicicletaDTO dto = new BicicletaDTO();

        when(biciRepository.findByEstacion("A1")).thenReturn(List.of(bici));
        when(mp.mapList(List.of(bici), BicicletaDTO.class)).thenReturn(List.of(dto));

        List<BicicletaDTO> result = bicicletaService.obtenerBicicletasPorEstacion("A1");

        assertEquals(1, result.size());
        verify(biciRepository).findByEstacion("A1");
    }

    @Test
    void reubicarBicicleta_debeActualizarEstacion() {
        bicicletaService.reubicarBicicleta(5L, "B4");
        verify(biciRepository).updateEstacionBici(5L, "B4");
    }

    @Test
    void bloquearBicicleta_debeActualizarCandadoTrue() {
        bicicletaService.bloquearBicicleta(3L);
        verify(biciRepository).updateCandado(3L, true);
    }

    @Test
    void desbloquearBicicleta_debeActualizarCandadoFalse() {
        bicicletaService.desbloquearBicicleta(3L);
        verify(biciRepository).updateCandado(3L, false);
    }

    @Test
    void habilitarBicicleta_debePonerDisponible() {
        bicicletaService.habilitarBicicleta(2L);
        verify(biciRepository).updateEstadoBici(2L, "DISPONIBLE");
    }

    @Test
    void inhabilitarBicicleta_debePonerNoDisponible() {
        bicicletaService.inhabilitarBicicleta(7L);
        verify(biciRepository).updateEstadoBici(7L, "NO DISPONIBLE");
    }

    @Test
    void usarBicicleta_debePonerEnUso() {
        bicicletaService.usarBicicleta(9L);
        verify(biciRepository).updateEstadoBici(9L, "EN USO");
    }

    @Test
    void recargarBicicleta_debeActualizarBateria() {
        bicicletaService.recargarBicicleta(1L, 80);
        verify(biciRepository).updateBateria(1L, 80);
    }
}