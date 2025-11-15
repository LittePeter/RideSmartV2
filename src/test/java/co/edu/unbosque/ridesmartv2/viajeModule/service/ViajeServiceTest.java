package co.edu.unbosque.ridesmartv2.viajeModule.service;

import co.edu.unbosque.ridesmartv2.bicicletaModule.service.InterfaceBiciService;
import co.edu.unbosque.ridesmartv2.config.ModelMapper;
import co.edu.unbosque.ridesmartv2.estacionModule.service.InterfaceEstacionService;
import co.edu.unbosque.ridesmartv2.viajeModule.model.dto.ReservaDTO;
import co.edu.unbosque.ridesmartv2.viajeModule.model.entity.Reserva;
import co.edu.unbosque.ridesmartv2.viajeModule.model.entity.Viaje;
import co.edu.unbosque.ridesmartv2.viajeModule.model.persistence.ViajeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class ViajeServiceTest {

    @Mock
    private ViajeRepository viajeRepo;
    @Mock
    private InterfaceBiciService biciService;
    @Mock
    private InterfaceReservaService reservaService;
    @Mock
    private InterfaceEstacionService estacionService;
    @Mock
    private ModelMapper mp;

    @InjectMocks
    private ViajeService viajeService;

    private Reserva reservaEntity;
    private ReservaDTO reservaDTO;

    @BeforeEach
    void setup() {
        reservaEntity = new Reserva();
        reservaEntity.setIdReserva(1L);
        reservaEntity.setFechaReserva(LocalDateTime.now().minusMinutes(15)); // expirada
    }

    @Test
    void iniciarViaje_reservaExpirada_debeCrearViaje() {

        // Simular que reservaService.getReserva() devuelve un DTO
        reservaDTO = new ReservaDTO();
        reservaDTO.setIdReserva(11111L);

        when(reservaService.getReserva(11111L)).thenReturn(reservaDTO);
        when(mp.map(reservaDTO, Reserva.class)).thenReturn(reservaEntity);

        // Simular save del repo
        Viaje viajeGuardado = new Viaje();
        when(viajeRepo.save(any(Viaje.class))).thenReturn(viajeGuardado);

        boolean resultado = viajeService.iniciarViaje(1L);

        assertTrue(resultado);

        // Verificamos que se llamen las dependencias correctas
        verify(reservaService).cumplirReserva(1L);
        verify(biciService).desbloquearBicicleta(any());
        verify(biciService).usarBicicleta(any());
        verify(viajeRepo).save(any(Viaje.class));
    }
}