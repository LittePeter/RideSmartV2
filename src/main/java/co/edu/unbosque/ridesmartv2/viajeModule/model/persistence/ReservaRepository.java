package co.edu.unbosque.ridesmartv2.viajeModule.model.persistence;

import co.edu.unbosque.ridesmartv2.viajeModule.model.entity.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    List<Reserva> findByIdUsuario_Identification(String identification);
    List <Reserva> findByIdEstacion_IdEstacion(String idEstacion);
    List <Reserva> findByIdBicicleta_IdBicicleta(long idBicicleta);

    @Transactional
    @Modifying
    @Query("UPDATE Reserva r SET r.estadoReserva = :estado WHERE r.idReserva = :idReserva")
    void updateEstadoReserva(long idReserva, String estado);
}
