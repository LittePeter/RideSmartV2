package co.edu.unbosque.ridesmartv2.bicicletaModule.model.persistence;

import co.edu.unbosque.ridesmartv2.bicicletaModule.model.entity.Bicicleta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface BicicletaRepository extends JpaRepository<Bicicleta, Long> {

    Bicicleta findById(long id);
    List<Bicicleta> findByEstacion(String estacion);
    List<Bicicleta> findByEstado(String estado);

    @Transactional
    @Modifying
    @Query("UPDATE Bicicleta b SET b.estacion = :estacion WHERE b.idBicicleta = :bicicletaId")
    void updateEstacionBici(long bicicletaId, String estacion);

    @Transactional
    @Modifying
    @Query("UPDATE Bicicleta b SET b.bateria = :bateria WHERE b.idBicicleta = :bicicletaId")
    void updateBateria(long bicicletaId, int bateria);

    @Transactional
    @Modifying
    @Query("UPDATE Bicicleta b SET b.estado = :estado WHERE b.idBicicleta = :bicicletaId")
    void updateEstadoBici(long bicicletaId, String estado);

    @Transactional
    @Modifying
    @Query("UPDATE Bicicleta b SET b.candado = :candado WHERE b.idBicicleta = :bicicletaId")
    void updateCandado(long bicicletaId, boolean candado);
}