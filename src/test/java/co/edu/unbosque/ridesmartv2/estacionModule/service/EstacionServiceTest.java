package co.edu.unbosque.ridesmartv2.estacionModule.service;

import co.edu.unbosque.ridesmartv2.config.ModelMapper;
import co.edu.unbosque.ridesmartv2.estacionModule.model.dto.EstacionDTO;
import co.edu.unbosque.ridesmartv2.estacionModule.model.entity.Estacion;
import co.edu.unbosque.ridesmartv2.estacionModule.model.persistence.EstacionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EstacionServiceTest {

    @Mock
    private EstacionRepository estacionRepository;

    @Mock
    private ModelMapper mp;

    @InjectMocks
    private EstacionService estacionService;

    private EstacionDTO estacionDTO;
    private Estacion estacionEntity;

    @BeforeEach
    void setup() {
        estacionDTO = new EstacionDTO();
        estacionDTO.setIdEstacion("EST1");
        estacionDTO.setNombre("Estacion Centro");
        estacionDTO.setCategoria("A");

        estacionEntity = new Estacion();
        estacionEntity.setIdEstacion("EST1");
        estacionEntity.setNombre("Estacion Centro");
        estacionEntity.setCategoria("A");
    }

    @Test
    void crearEstacion_deberiaGuardarEntidad() {

        when(mp.map(estacionDTO, Estacion.class)).thenReturn(estacionEntity);

        estacionService.crearEstacion(estacionDTO);

        verify(estacionRepository).save(estacionEntity);
    }

    @Test
    void obtenerEstacion_deberiaRetornarDTO() {

        when(estacionRepository.findByNombre("Estacion Centro")).thenReturn(estacionEntity);
        when(mp.map(estacionEntity, EstacionDTO.class)).thenReturn(estacionDTO);

        EstacionDTO result = estacionService.obtenerEstacion("Estacion Centro");

        assertNotNull(result);
        assertEquals("Estacion Centro", result.getNombre());
    }

    @Test
    void obtenerEstacionPorCategoria_deberiaRetornarListaDTO() {

        List<Estacion> estacionesEntity = List.of(estacionEntity);
        List<EstacionDTO> estacionesDTO = List.of(estacionDTO);

        when(estacionRepository.findByCategoria("A")).thenReturn(estacionesEntity);
        when(mp.mapList(estacionesEntity, EstacionDTO.class)).thenReturn(estacionesDTO);

        List<EstacionDTO> result = estacionService.obtenerEstacionPorCategoria("A");

        assertEquals(1, result.size());
        assertEquals("Estacion Centro", result.get(0).getNombre());
    }

    @Test
    void obtenerEstacionPorEstado_deberiaRetornarListaDTO() {

        List<Estacion> estacionesEntity = List.of(estacionEntity);
        List<EstacionDTO> estacionesDTO = List.of(estacionDTO);

        when(estacionRepository.findByEstado("DISPONIBLE")).thenReturn(estacionesEntity);
        when(mp.mapList(estacionesEntity, EstacionDTO.class)).thenReturn(estacionesDTO);

        List<EstacionDTO> result = estacionService.obtenerEstacionPorEstado("DISPONIBLE");

        assertEquals(1, result.size());
    }

    @Test
    void habilitarEstacion_deberiaActualizarEstado() {

        estacionService.habilitarEstacion("EST1");

        verify(estacionRepository).updateEstado("EST1", "DISPONIBLE");
    }

    @Test
    void desHabilitarEstacion_deberiaActualizarEstado() {

        estacionService.desHabilitarEstacion("EST1");

        verify(estacionRepository).updateEstado("EST1", "NO DISPONIBLE");
    }

    @Test
    void generarOrdenRedistribucion_estacionSinBicis_noHaceNadaPeroNoFalla() {

        estacionDTO.setBiciList(List.of()); // sin bicis

        when(estacionRepository.findByNombre("EST1")).thenReturn(estacionEntity);
        when(mp.map(estacionEntity, EstacionDTO.class)).thenReturn(estacionDTO);

        assertDoesNotThrow(() -> estacionService.generarOrdenRedistribucion("EST1"));
    }
}