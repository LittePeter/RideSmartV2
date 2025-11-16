package co.edu.unbosque.ridesmartv2.pagoModule.model.dto;

import co.edu.unbosque.ridesmartv2.viajeModule.model.entity.Viaje;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
/**
 * Objeto de transferencia de datos (DTO) para la entidad Pago.
 * <p>
 * Se utiliza para exponer información de un pago a través de la API REST,
 * sin exponer directamente la entidad ni sus relaciones JPA.
 * </p>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagoDTO {

    private Viaje viaje;
    private double costo;
    private double impuestos;
    private double costoUSD;
    private double impuestosUSD;
    private LocalDateTime fecha;
    private String estado;
    private String idStripe;
}
