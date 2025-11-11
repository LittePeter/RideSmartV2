package co.edu.unbosque.ridesmartv2.viajeModule.model.persistence;

import co.edu.unbosque.ridesmartv2.viajeModule.model.entity.Viaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ViajeRepository extends JpaRepository<Viaje, Long> {

    Viaje findByIdViaje(long id);
    List<Viaje> findByUsuario(String usuario);
    List<Viaje> findByBicicleta(long bicicleta);
    List<Viaje> findByEstacionInicioOrEstacionFin(long estacionInicio, long estacionFin);

    @Transactional
    @Modifying
    @Query("UPDATE Viaje v SET v.estado = :estado WHERE v.idViaje = :idViaje")
    void actualizarEstado(long idViaje, String estado);

    @Transactional
    @Modifying
    @Query("UPDATE Viaje v SET v.precio = :precio WHERE v.idViaje = :idViaje")
    void actualizarPrecio(long idViaje, double precio);

    @Transactional
    @Modifying
    @Query("UPDATE Viaje v SET v.estacionFin = :estacionFin WHERE v.idViaje = :idViaje")
    void actualizarEstacionFin(long idViaje, long estacionFin);

    @Transactional
    @Modifying
    @Query("UPDATE Viaje v SET v.pago = :pago WHERE v.idViaje = :idViaje")
    void actualizarPago(long idViaje, long pago);

    @Transactional
    @Modifying
    @Query("UPDATE Viaje v SET v.estado = :estado, v.precio = :precio, v.estacionFin = :estacionFin, v.pago = :pago WHERE v.idViaje = :idViaje")
    void actualizarViajeCompleto(long idViaje, String estado, double precio, long estacionFin, long pago);

    @Transactional
    @Modifying
    @Query ("UPDATE Viaje v SET v.duracion = :duracion WHERE v.idViaje = :idViaje")
    void actualizarDuracion(long idViaje, long duracion);

    @Transactional
    @Modifying
    @Query ("UPDATE Viaje v SET v.fechaFin = :fechaFin WHERE v.idViaje = :idViaje")
    void actualizarFechaFin(long idViaje, LocalDateTime fechaFin);
}
