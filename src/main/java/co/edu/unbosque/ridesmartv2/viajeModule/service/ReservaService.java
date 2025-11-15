package co.edu.unbosque.ridesmartv2.viajeModule.service;

import co.edu.unbosque.ridesmartv2.bicicletaModule.model.dto.BicicletaDTO;
import co.edu.unbosque.ridesmartv2.bicicletaModule.model.entity.Bicicleta;
import co.edu.unbosque.ridesmartv2.bicicletaModule.service.InterfaceBiciService;
import co.edu.unbosque.ridesmartv2.config.ModelMapper;
import co.edu.unbosque.ridesmartv2.viajeModule.model.dto.ReservaDTO;
import co.edu.unbosque.ridesmartv2.viajeModule.model.entity.Reserva;
import co.edu.unbosque.ridesmartv2.viajeModule.model.persistence.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReservaService implements  InterfaceReservaService {

    @Autowired
    private ReservaRepository reservaRepository;
    @Autowired
    private InterfaceBiciService biciService;
    @Autowired
    private ModelMapper mp;

    @Override
    @Transactional
    public boolean createReserva(ReservaDTO reservaDTO){

        boolean isAbleToCreate = false;
        List<ReservaDTO> reservasByUser = getReservasByUsuario(String.valueOf(reservaDTO.getIdUsuario().getIdentification()));
        List<ReservaDTO> resActivas = reservasByUser.stream()
                .filter(r -> r.getEstadoReserva().equalsIgnoreCase("CONFIRMADA"))
                .toList();

        if (resActivas.isEmpty()){
            isAbleToCreate = true;
        } else {
            return false;
        }

        if (isAbleToCreate){
            Reserva reserva = mp.map(reservaDTO, Reserva.class);
            reserva.setFechaReserva(LocalDateTime.now());
            reserva.setEstadoReserva("CONFIRMADA");

            List<BicicletaDTO> bicicletas = biciService.obtenerBicicletasPorEstacion(reservaDTO.getIdEstacion().getIdEstacion());
            List<BicicletaDTO> bicisDisponibles = bicicletas.stream()
                    .filter(b -> b.getEstado().equalsIgnoreCase("DISPONIBLE")
                            && b.getBateria() > 40)
                    .toList();
            if (bicisDisponibles.isEmpty()){
                return  false;
            } else {
                Bicicleta bici = mp.map(bicisDisponibles.get(0), Bicicleta.class);
                reserva.setIdBicicleta(bici);
                biciService.inhabilitarBicicleta(bici.getIdBicicleta());
            }
            return reservaRepository.save(reserva).equals(reserva);
        } else  {
            return false;
        }
    }

    @Override
    public List<ReservaDTO> getReservas(){
        List<ReservaDTO> resDTO = mp.mapList(reservaRepository.findAll(), ReservaDTO.class);
        return resDTO;
    }

    @Override
    public List<ReservaDTO> getReservasByUsuario(String idUsuario){
        List <ReservaDTO> resDTO = mp.mapList(reservaRepository.findByIdUsuario_Identification(idUsuario), ReservaDTO.class);
        return resDTO;
    }

    @Override
    public List<ReservaDTO> getReservasByBicicleta(long bicicleta){
        List <ReservaDTO> resDTO = mp.mapList(reservaRepository.findByIdBicicleta_IdBicicleta(bicicleta), ReservaDTO.class);
        return resDTO;
    }

    @Override
    public List<ReservaDTO> getReservasByEstacion(String estacion){
        List <ReservaDTO> resDTO = mp.mapList(reservaRepository.findByIdEstacion_IdEstacion(estacion), ReservaDTO.class);
        return resDTO;
    }

    @Override
    public ReservaDTO getReserva(long idReserva){
        ReservaDTO resDTO = mp.map(reservaRepository.findById(idReserva), ReservaDTO.class);
        return resDTO;
    }

    @Override
    public boolean cancelarReserva(long idReserva){
        try {
            reservaRepository.updateEstadoReserva(idReserva, "CANCELADA");
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean cumplirReserva(long idReserva){
        try {
            reservaRepository.updateEstadoReserva(idReserva, "CUMPLIDA");
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean expirarReserva(long idReserva){
        try {
            reservaRepository.updateEstadoReserva(idReserva, "EXPIRADA");
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}