package co.edu.unbosque.ridesmartv2.estacionModule.model.entity;

import co.edu.unbosque.ridesmartv2.bicicletaModule.model.entity.Bicicleta;
import co.edu.unbosque.ridesmartv2.viajeModule.model.entity.Reserva;
import co.edu.unbosque.ridesmartv2.viajeModule.model.entity.Viaje;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
/**
 * Representa una estación del sistema RideSmart donde se ubican las bicicletas.
 * <p>
 * Cada estación tiene un identificador único (ID de tipo {@code String}), nombre, categoría,
 * ubicación geográfica (latitud/longitud), estado y capacidad.
 * Está relacionada con listas de {@link Bicicleta}, {@link Reserva} y {@link Viaje}.
 * </p>
 */
@Entity
@Table (name = "Estacion")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Estacion {

    @Id
    private String idEstacion;
    private String nombre;
    private String categoria;
    private int capacidad;
    private double latitud;
    private double longitud;
    private String estado;

    @OneToMany(mappedBy = "idEstacion", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    private List<Bicicleta> biciList;

    @OneToMany(mappedBy = "idEstacion")
    private List<Reserva> reserva;

    @OneToMany(mappedBy = "idViaje")
    private List<Viaje> viajeList;
}
