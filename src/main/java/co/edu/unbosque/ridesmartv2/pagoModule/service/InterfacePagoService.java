package co.edu.unbosque.ridesmartv2.pagoModule.service;

import co.edu.unbosque.ridesmartv2.pagoModule.model.dto.PagoDTO;
import com.stripe.exception.StripeException;

import java.util.List;
/**
 * Define el contrato de servicios para la gestión de pagos en el sistema RideSmart.
 */
public interface InterfacePagoService {
    /**
     * Crea un registro de pago asociado a un viaje específico.
     *
     * @param idViaje el ID del viaje para el que se crea el pago.
     * @return {@code true} si el pago se crea exitosamente.
     */
    boolean crearPago(long idViaje);
    /**
     * Obtiene un pago por su ID.
     *
     * @param idPago el ID del pago.
     * @return un {@link PagoDTO} con los datos del pago, o {@code null} si no existe.
     */
    PagoDTO getPagoById(long idPago);
    /**
     * Obtiene el pago asociado a un viaje.
     *
     * @param viaje el ID del viaje.
     * @return un {@link PagoDTO} con los datos del pago.
     */
    PagoDTO getPagoByViaje(long viaje);
    /**
     * Obtiene todos los pagos del sistema.
     *
     * @return una lista de {@link PagoDTO}.
     */
    List<PagoDTO> getAllPagos();
    /**
     * Obtiene un pago por su ID de Stripe.
     *
     * @param idStripe el ID del pago en Stripe.
     * @return un {@link PagoDTO} con los datos del pago.
     */
    PagoDTO getPagoByIdStripe(String idStripe);
    /**
     * Inicia el proceso de pago con Stripe para un pago específico.
     *
     * @param idPago el ID del pago a procesar.
     * @return {@code true} si el intento de pago se crea exitosamente.
     * @throws com.stripe.exception.StripeException si ocurre un error al comunicarse con Stripe.
     */
    boolean completarPago(long idPago) throws StripeException;
}
