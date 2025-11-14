package co.edu.unbosque.ridesmartv2.viajeModule.service;

import co.edu.unbosque.ridesmartv2.bicicletaModule.service.InterfaceBiciService;
import co.edu.unbosque.ridesmartv2.estacionModule.model.entity.Estacion;
import co.edu.unbosque.ridesmartv2.estacionModule.service.InterfaceEstacionService;
import co.edu.unbosque.ridesmartv2.config.ModelMapper;
import co.edu.unbosque.ridesmartv2.viajeModule.model.dto.ViajeDTO;
import co.edu.unbosque.ridesmartv2.viajeModule.model.entity.Reserva;
import co.edu.unbosque.ridesmartv2.viajeModule.model.entity.Viaje;
import co.edu.unbosque.ridesmartv2.viajeModule.model.persistence.ViajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ViajeService implements InterfaceViajeService{

    @Autowired
    private ViajeRepository viajeRepo;

    @Autowired
    private InterfaceBiciService biciService;

    @Autowired
    private InterfaceReservaService reservaService;

    @Autowired
    private InterfaceEstacionService estacionService;

    @Autowired
    private ModelMapper mp;

    @Override
    @Transactional
    public boolean iniciarViaje(long idReserva) {

        Reserva reserva = mp.map(reservaService.getReserva(idReserva), Reserva.class);
        LocalDateTime fechaExpiracion = reserva.getFechaReserva().plusMinutes(10);

        if (fechaExpiracion.isBefore(LocalDateTime.now())) {
            reservaService.cumplirReserva(reserva.getIdReserva());
            biciService.desbloquearBicicleta(reserva.getIdBicicleta().getIdBicicleta());
            biciService.usarBicicleta(reserva.getIdBicicleta().getIdBicicleta());

            Viaje viaje = new Viaje();
            viaje.setIdReserva(reserva);
            viaje.setFechaInicio(LocalDateTime.now());
            return  viajeRepo.save(viaje).equals(viaje);
        } else {
            reservaService.expirarReserva(reserva.getIdReserva());
            biciService.habilitarBicicleta(reserva.getIdBicicleta().getIdBicicleta());
            return false;
        }
    }

    @Override
    public ViajeDTO obtenerViaje(long idViaje) {
        ViajeDTO viajeDTO = mp.map(viajeRepo.findById(idViaje), ViajeDTO.class);
        return  viajeDTO;
    }

    @Override
    public ViajeDTO obtenerViajePorReserva(long idReserva) {
        ViajeDTO viaje = mp.map(viajeRepo.findByIdReserva_IdReserva(idReserva), ViajeDTO.class);
        return viaje;
    }

    @Override
    public List<ViajeDTO> obtenerViajePorEstacionFin(String idEstacion) {
        List<ViajeDTO> viajes = mp.mapList(viajeRepo.findByIdEstacionFin_IdEstacion(idEstacion), ViajeDTO.class);
        return viajes;
    }

    @Override
    public List<ViajeDTO> obtenerViajePorTipoViaje(String tipoViaje) {
        List<ViajeDTO> viajes = mp.mapList(viajeRepo.findByTipoViaje(tipoViaje), ViajeDTO.class);
        return viajes;
    }

    @Override
    @Transactional
    public boolean finalizarViaje(long idViaje, String estacionFin  ) {

        Viaje viaje = mp.map(obtenerViaje(idViaje), Viaje.class);
        Reserva reserva = mp.map(viaje.getIdReserva(), Reserva.class);
        Long bici = reserva.getIdBicicleta().getIdBicicleta();

        viaje.setFechaFin(LocalDateTime.now());
        viaje.setIdEstacionFin(mp.map(estacionService.obtenerEstacion(estacionFin),  Estacion.class));

        long duracion = Duration.between(viaje.getFechaInicio(), viaje.getFechaFin()).toMinutes();
        String tipoViaje = reserva.getTipoViaje();

        viaje.setDuracion((int)duracion);
        viaje.setCosto(calcularCosto(tipoViaje, duracion));
        //TODO:viaje.setPago(); implementar pagos

        biciService.bloquearBicicleta(bici);
        biciService.habilitarBicicleta(bici);
        biciService.reubicarBicicleta(bici, estacionFin);
        viajeRepo.save(viaje);
        return true;
    }

    @Override
    public Viaje getEntityById(long idViaje) {
        return viajeRepo.findById(idViaje).orElse(null);
    }

    private double calcularCosto(String tipoViaje, long duracion) {

        double costo;
        double precioBase;
        double minAdicional;

        if (tipoViaje.equalsIgnoreCase("LARGO")) {

            precioBase = 25000;
            minAdicional = 1000;
            if (duracion <= 75) {
                costo = precioBase;
            } else {
                int minExtra = (int) duracion - 75;
                costo = (precioBase + (minAdicional * minExtra));
            }
            return costo;
        } else if (tipoViaje.equalsIgnoreCase("ULT. MILLA")) {

            precioBase = 17500;
            minAdicional = 250;
            if (duracion <= 45) {
                costo = precioBase;
            } else {
                int minExtra = (int) duracion - 45;
                costo = (precioBase + (minAdicional * minExtra));
            }
            return costo;
        } else {
            return 0;
        }
    }
}