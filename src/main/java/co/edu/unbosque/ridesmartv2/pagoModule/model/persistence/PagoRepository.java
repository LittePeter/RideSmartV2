package co.edu.unbosque.ridesmartv2.pagoModule.model.persistence;

import co.edu.unbosque.ridesmartv2.pagoModule.model.entity.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/**
 * Repositorio de datos para la entidad {@link Pago}.
 * <p>
 * Extiende {@code JpaRepository} y define métodos personalizados para consultas específicas:
 * por viaje, por ID de Stripe y actualización de estado.
 * </p>
 */
@Repository
public interface PagoRepository extends JpaRepository<Pago, Long> {

    Pago findByIdViaje_IdViaje(long idViaje);
    List<Pago> findAll();
    Pago findById(long id);
    Pago findByIdStripe(String idStripe);
    //Pago updatePago(Pago pago);

    @Transactional
    @Modifying
    @Query("UPDATE Pago p SET p.estado = :estado WHERE p.idPago = :idPago")
    Pago updateEstadoPago(long idPago, String estado);
}
