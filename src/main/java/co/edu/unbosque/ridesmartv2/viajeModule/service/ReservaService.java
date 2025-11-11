package co.edu.unbosque.ridesmartv2.viajeModule.service;


import co.edu.unbosque.ridesmartv2.viajeModule.model.dto.InfoReservaDTO;
import co.edu.unbosque.ridesmartv2.viajeModule.model.dto.ReservaDTO;
import co.edu.unbosque.ridesmartv2.viajeModule.model.entity.Reserva;
import co.edu.unbosque.ridesmartv2.viajeModule.model.persistence.ReservaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReservaService implements ReservaServiceI{

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private ModelMapper mp;

    @Override
    public ReservaDTO crearReserva(InfoReservaDTO info) {

        Reserva res = new Reserva();

        res.setUsuario(info.getUsuario());
        res.setEstacion(info.getEstacion());
        res.setTipoViaje(info.getTipoViaje());
        LocalDateTime fechaReserva = LocalDateTime.now();
        res.setFechaReserva(fechaReserva);
        res.setFechaVencimiento(fechaReserva.plusMinutes(10));
        res.setEstadoReserva("ACTIVA");
        reservaRepository.save(res);
        return mp.map(res, ReservaDTO.class);
    }

    public void expirarReserva(long idReserva) {
        reservaRepository.updateEstadoReserva(idReserva, "EXPIRADA");
    }

    public void cancelarReserva(long idReserva) {
        reservaRepository.updateEstadoReserva(idReserva, "CANCELADA");
    }

    public void cumplirReserva(long idReserva) {
        reservaRepository.updateEstadoReserva(idReserva, "CUMPLIDA");
    }

    public ReservaDTO obtenerReserva(long idReserva) {
        return mp.map(reservaRepository.findByIdReserva(idReserva), ReservaDTO.class);
    }

    public List<ReservaDTO> obtenerReservas() {
        return reservaRepository
                .findAll()
                .stream()
                .map(r -> mp.map(r,ReservaDTO.class)).toList();
    }
}
