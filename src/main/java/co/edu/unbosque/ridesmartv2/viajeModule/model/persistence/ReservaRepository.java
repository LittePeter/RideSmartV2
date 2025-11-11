package co.edu.unbosque.ridesmartv2.viajeModule.model.persistence;

import co.edu.unbosque.ridesmartv2.viajeModule.model.entity.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    List<Reserva> findByUsuario(String Usuario);
    Reserva findByIdReserva(Long idReserva);
    List<Reserva> findByBicicletaId(Long bicicletaId);

    @Transactional
    @Modifying
    @Query("UPDATE Reserva r SET r.estadoReserva = :estado WHERE r.idReserva = :idReserva")
    int updateEstadoReserva(@Param("idReserva") Long idReserva, @Param("estadoReserva") String estadoReserva);
}
