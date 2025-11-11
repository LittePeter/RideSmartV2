package co.edu.unbosque.ridesmartv2.viajeModule.service;


import co.edu.unbosque.ridesmartv2.viajeModule.model.dto.InfoFinViajeDTO;
import co.edu.unbosque.ridesmartv2.viajeModule.model.dto.InfoInitViajeDTO;

import co.edu.unbosque.ridesmartv2.viajeModule.model.dto.ViajeDTO;
import co.edu.unbosque.ridesmartv2.viajeModule.model.entity.Reserva;
import co.edu.unbosque.ridesmartv2.viajeModule.model.entity.Viaje;
import co.edu.unbosque.ridesmartv2.viajeModule.model.persistence.ReservaRepository;
import co.edu.unbosque.ridesmartv2.viajeModule.model.persistence.ViajeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ViajeService implements ViajeServiceI {

    @Autowired
    private ReservaService reservaService;
    @Autowired
    private ViajeRepository viajeRepository;
    @Autowired
    private ReservaRepository reservaRepository;
    @Autowired
    private ModelMapper mp;

    @Override
    public ViajeDTO iniciarViaje (InfoInitViajeDTO info) {
        Reserva reserva = reservaRepository.findByIdReserva(info.getReserva());
        if (reserva != null) {
            if (reserva.getEstadoReserva().equalsIgnoreCase("ACTIVA")) {
                Viaje viaje = new Viaje();
                viaje.setUsuario(reserva.getUsuario());
                viaje.setBicicleta(info.getBicicleta());
                viaje.setEstacionInicio(reserva.getEstacion());
                viaje.setFechaInicio(LocalDateTime.now());
                viaje.setTipoViaje(reserva.getTipoViaje());
                viaje.setEstado("INICIADO");
                viajeRepository.save(viaje);
                return mp.map(viaje, ViajeDTO.class);
            } else {
                return null;
            }
        }
        return null;
    }

    @Transactional
    @Override
    public void finalizarViaje (InfoFinViajeDTO info) {

        long viajeId = info.getViajeId();
        Viaje viaje = mp.map(obtenerViaje(info.getViajeId()), Viaje.class);
        if (viaje != null){
            if (viaje.getEstado().equals("INICIADO")) {
                viajeRepository.actualizarEstado(viajeId, "PENDIENTE DE PAGO");
                viajeRepository.actualizarEstacionFin(viajeId, info.getEstacionFin());
                viajeRepository.actualizarFechaFin(viajeId, LocalDateTime.now());
                long duracion = Duration.between(viaje.getFechaInicio(), viaje.getFechaFin()).toMinutes();
                viajeRepository.actualizarDuracion(viajeId, duracion);
                viajeRepository.actualizarPrecio(viajeId, calcularPrecio(viaje.getTipoViaje(), duracion));
            }
        }

        //#TODO: Implementar pagos
    }

    @Override
    public ViajeDTO obtenerViaje (long viajeId) {
        return mp.map(viajeRepository.findByIdViaje(viajeId), ViajeDTO.class);
    }
    @Override
    public List<ViajeDTO> obtenerViajes () {
        return viajeRepository
                .findAll()
                .stream()
                .map(v -> mp.map(v, ViajeDTO.class)).toList();
    }

    private double calcularPrecio(String tipoViaje, long duracion) {

        double costo;
        double precioBase;
        double minAdicional;
        double impuesto = 1.19;

        if (tipoViaje.equalsIgnoreCase("LARGO")) {

            precioBase = 25000;
            minAdicional = 1000;
            if (duracion <= 75){
                costo = precioBase * impuesto;
            }
            else {
                int minExtra = (int)duracion - 75;
                costo = (precioBase + (minAdicional * minExtra)) * impuesto;
            }
            return costo;
        }
        else if (tipoViaje.equalsIgnoreCase("ULT. MILLA")) {

            precioBase = 17500;
            minAdicional = 250;
            if (duracion <= 45){
                costo = precioBase * impuesto;
            }
            else {
                int minExtra = (int)duracion - 45;
                costo = (precioBase + (minAdicional * minExtra)) * impuesto;
            }
            return costo;
        }
        return 0;
    }
}
