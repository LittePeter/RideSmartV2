package co.edu.unbosque.ridesmartv2.estacionModule.model.persistence;

import co.edu.unbosque.ridesmartv2.estacionModule.model.entity.Estacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface EstacionRepository extends JpaRepository<Estacion, Long> {

    Estacion findByNombre(String nombre);
    List<Estacion> findByCategoria(String categoria);
    List<Estacion> findByEstado(String estado);

    @Transactional
    @Modifying
    @Query("UPDATE Estacion E SET E.estado = :estado WHERE E.idEstacion = :idEstacion")
    void updateEstado(String idEstacion, String estado);

    @Transactional
    @Modifying
    @Query("UPDATE Estacion E SET E.latitud = :latitud, E.longitud = :longitud WHERE E.idEstacion = :idEstacion")
    void updateUbicacion (String idEstacion, double latitud, double longitud);

}