package co.edu.unbosque.ridesmartv2.viajeModule.model.persistence;

import co.edu.unbosque.ridesmartv2.viajeModule.model.entity.Viaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * Repositorio de datos para la entidad {@link Viaje}.
 * <p>
 * Extiende {@code JpaRepository} y define métodos personalizados para consultas específicas:
 * por reserva, tipo de viaje y estación de fin.
 * </p>
 */
@Repository
public interface ViajeRepository extends JpaRepository<Viaje, Long> {

    Viaje findByIdReserva_IdReserva(long idReserva);
    List<Viaje> findByTipoViaje(String tipoViaje);
    List<Viaje> findByIdEstacionFin_IdEstacion(String idEstacionFin);
    //Viaje updateViaje(Viaje viaje);
}
