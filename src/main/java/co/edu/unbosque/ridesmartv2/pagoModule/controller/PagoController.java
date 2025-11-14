package co.edu.unbosque.ridesmartv2.pagoModule.controller;

import co.edu.unbosque.ridesmartv2.pagoModule.model.dto.PagoDTO;
import co.edu.unbosque.ridesmartv2.pagoModule.service.InterfacePagoService;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/pagos")
public class PagoController {

    @Autowired
    private InterfacePagoService pagoService;

    @PostMapping("/crear/{idViaje}")
    public ResponseEntity<?> crearPago(@PathVariable long idViaje) {

        boolean creado = pagoService.crearPago(idViaje);

        if (!creado) {
            return ResponseEntity.badRequest().body("No fue posible generar el pago");
        }

        return ResponseEntity.ok("Pago generado correctamente para el viaje " + idViaje);
    }
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

    @GetMapping("/{idPago}")
    public ResponseEntity<?> getPago(@PathVariable long idPago) {

        PagoDTO pago = pagoService.getPagoById(idPago);

        if (pago == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(pago);
    }

    @GetMapping("/viaje/{idViaje}")
    public ResponseEntity<?> getPagoByViaje(@PathVariable long idViaje) {

        PagoDTO pago = pagoService.getPagoByViaje(idViaje);

        if (pago == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(pago);
    }

    @GetMapping("/stripe/{idStripe}")
    public ResponseEntity<?> getPagoByStripeId(@PathVariable String idStripe) {

        PagoDTO pago = pagoService.getPagoByIdStripe(idStripe);

        if (pago == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(pago);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllPagos() {

        return ResponseEntity.ok(pagoService.getAllPagos());
    }
}