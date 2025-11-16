package co.edu.unbosque.ridesmartv2.pagoModule.controller;

import co.edu.unbosque.ridesmartv2.pagoModule.model.dto.PagoDTO;
import co.edu.unbosque.ridesmartv2.pagoModule.service.InterfacePagoService;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador REST para la gestión de pagos.
 * <p>
 * Expone endpoints para crear, procesar y consultar pagos.
 * </p>
 */
@RestController
@RequestMapping("/api/pagos")
public class PagoController {

    @Autowired
    private InterfacePagoService pagoService;
    /**
     * Crea un registro de pago pendiente para un viaje.
     *
     * @param idViaje el ID del viaje asociado al pago.
     * @return un mensaje de éxito o error según el resultado.
     */
    @PostMapping("/crear/{idViaje}")
    public ResponseEntity<?> crearPago(@PathVariable long idViaje) {

        boolean creado = pagoService.crearPago(idViaje);

        if (!creado) {
            return ResponseEntity.badRequest().body("No fue posible generar el pago");
        }

        return ResponseEntity.ok("Pago generado correctamente para el viaje " + idViaje);
    }
    /**
     * Inicia el proceso de pago con Stripe para un pago existente.
     *
     * @param idPago el ID del pago a procesar.
     * @return el {@link PagoDTO} actualizado o un mensaje de error.
     */
    @PostMapping("/procesar/{idPago}")
    public ResponseEntity<?> procesarPago(@PathVariable long idPago) {

        try {
            boolean ok = pagoService.completarPago(idPago);

            if (!ok) {
                return ResponseEntity.badRequest().body("No fue posible iniciar el proceso de pago.");
            }

            PagoDTO pagoDTO = pagoService.getPagoById(idPago);
            return ResponseEntity.ok(pagoDTO);

        } catch (StripeException e) {
            return ResponseEntity.badRequest().body("Error Stripe: " + e.getMessage());
        }
    }
    /**
     * Obtiene un pago por su ID.
     *
     * @param idPago el ID del pago.
     * @return el {@link PagoDTO} (200) o 404 si no se encuentra.
     */
    @GetMapping("/{idPago}")
    public ResponseEntity<?> getPago(@PathVariable long idPago) {

        PagoDTO pago = pagoService.getPagoById(idPago);

        if (pago == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(pago);
    }
    /**
     * Obtiene el pago asociado a un viaje.
     *
     * @param idViaje el ID del viaje.
     * @return el {@link PagoDTO} (200) o 404 si no existe.
     */
    @GetMapping("/viaje/{idViaje}")
    public ResponseEntity<?> getPagoByViaje(@PathVariable long idViaje) {

        PagoDTO pago = pagoService.getPagoByViaje(idViaje);

        if (pago == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(pago);
    }
    /**
     * Obtiene un pago por su ID de Stripe.
     *
     * @param idStripe el ID del pago en Stripe.
     * @return el {@link PagoDTO} (200) o 404 si no se encuentra.
     */
    @GetMapping("/stripe/{idStripe}")
    public ResponseEntity<?> getPagoByStripeId(@PathVariable String idStripe) {

        PagoDTO pago = pagoService.getPagoByIdStripe(idStripe);

        if (pago == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(pago);
    }
    /**
     * Obtiene todos los pagos del sistema.
     *
     * @return una lista de {@link PagoDTO}.
     */
    @GetMapping("/all")
    public ResponseEntity<?> getAllPagos() {

        return ResponseEntity.ok(pagoService.getAllPagos());
    }
}