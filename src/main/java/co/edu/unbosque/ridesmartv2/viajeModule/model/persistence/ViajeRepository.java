package co.edu.unbosque.ridesmartv2.viajeModule.model.persistence;

import co.edu.unbosque.ridesmartv2.viajeModule.model.entity.Viaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ViajeRepository extends JpaRepository<Viaje, Long> {

    Viaje findByIdReserva_IdReserva(long idReserva);
    List<Viaje> findByTipoViaje(String tipoViaje);
    List<Viaje> findByIdEstacionFin_IdEstacion(String idEstacionFin);
    //Viaje updateViaje(Viaje viaje);
}
