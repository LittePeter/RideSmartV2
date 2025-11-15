package co.edu.unbosque.ridesmartv2.viajeModule.service;

import co.edu.unbosque.ridesmartv2.bicicletaModule.model.dto.BicicletaDTO;
import co.edu.unbosque.ridesmartv2.bicicletaModule.model.entity.Bicicleta;
import co.edu.unbosque.ridesmartv2.bicicletaModule.service.InterfaceBiciService;
import co.edu.unbosque.ridesmartv2.config.ModelMapper;
import co.edu.unbosque.ridesmartv2.viajeModule.model.dto.ReservaDTO;
import co.edu.unbosque.ridesmartv2.viajeModule.model.entity.Reserva;
import co.edu.unbosque.ridesmartv2.viajeModule.model.persistence.ReservaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReservaServiceTest {

    @Mock
    private ReservaRepository reservaRepository;

    @Mock
    private InterfaceBiciService biciService;

    @Mock
    private ModelMapper mp;

    @InjectMocks
    private ReservaService reservaService;

    private ReservaDTO reservaDTO;
    private Reserva reservaEntity;

    @BeforeEach
    void setup() {

        // Reserva DTO inicial
        reservaDTO = new ReservaDTO();
        // Simulación de sus subclases
        var userDTO = new co.edu.unbosque.ridesmartv2.userModule.model.dto.UserDto();
        userDTO.setIdentification("123");
        reservaDTO.setIdUsuario(userDTO);

        var estacionDTO = new co.edu.unbosque.ridesmartv2.estacionModule.model.dto.EstacionDTO();
        estacionDTO.setIdEstacion("EST1");
        reservaDTO.setIdEstacion(estacionDTO);

        // Reserva Entity mapeada
        reservaEntity = new Reserva();
        reservaEntity.setIdReserva(1L);
    }

    @Test
    void createReserva_usuarioSinReservasActivas_creaReserva() {

        // No tiene reservas activas
        when(reservaService.getReservasByUsuario("123"))
                .thenReturn(List.of());

        // Simular conversión DTO → Entity
        when(mp.map(reservaDTO, Reserva.class)).thenReturn(reservaEntity);

        // Bicicletas disponibles en la estación
        BicicletaDTO biciDTO = new BicicletaDTO();
        biciDTO.setIdBicicleta(10L);
        biciDTO.setEstado("DISPONIBLE");
        biciDTO.setBateria(80);

        when(biciService.obtenerBicicletasPorEstacion("EST1"))
                .thenReturn(List.of(biciDTO));

        // Mapeo BicicletaDTO → Bicicleta
        Bicicleta biciEntity = new Bicicleta();
        biciEntity.setIdBicicleta(10L);

        when(mp.map(biciDTO, Bicicleta.class)).thenReturn(biciEntity);

        // save()
        when(reservaRepository.save(any(Reserva.class))).thenReturn(reservaEntity);

        // Ejecutar
        boolean result = reservaService.createReserva(reservaDTO);

        // Validar
        assertTrue(result, "La reserva debería crearse correctamente");

        // Verificaciones
        verify(biciService).inhabilitarBicicleta(10L);
        verify(reservaRepository).save(any(Reserva.class));
    }
}