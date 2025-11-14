package co.edu.unbosque.ridesmartv2.pagoModule.model.entity;

import co.edu.unbosque.ridesmartv2.viajeModule.model.entity.Viaje;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "Pago")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idPago;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idViaje", referencedColumnName = "idViaje")
    private Viaje idViaje;

    private double costo;
    private double impuestos;
    private double costoUSD;
    private double impuestosUSD;
    private LocalDateTime fecha;
    private String estado;
    private String idStripe;
}
