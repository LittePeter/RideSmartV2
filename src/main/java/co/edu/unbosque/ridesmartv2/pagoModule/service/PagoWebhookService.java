package co.edu.unbosque.ridesmartv2.pagoModule.service;

import co.edu.unbosque.ridesmartv2.pagoModule.model.entity.Pago;
import co.edu.unbosque.ridesmartv2.pagoModule.model.persistence.PagoRepository;
import co.edu.unbosque.ridesmartv2.viajeModule.service.InterfaceViajeService;
import com.stripe.model.Event;
import com.stripe.model.PaymentIntent;
import com.stripe.net.ApiResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * Servicio encargado de procesar los eventos webhook enviados por Stripe.
 * <p>
 * Escucha eventos como {@code payment_intent.succeeded} y {@code payment_intent.payment_failed}
 * para actualizar el estado de los pagos en el sistema.
 * </p>
 */
@Service
public class PagoWebhookService {

    @Autowired
    private PagoRepository pagoRepo;
    @Autowired
    private InterfaceViajeService viajeService;

    public void procesarEvento(Event event) {

        switch (event.getType()) {

            case "payment_intent.succeeded":
                procesarPaymentSucceeded(event);
                break;

            case "payment_intent.payment_failed":
                procesarPaymentFailed(event);
                break;

            default:
                System.out.println("Evento no manejado: " + event.getType());
        }
    }

    private void procesarPaymentSucceeded(Event event) {

        PaymentIntent intent = ApiResource.GSON.fromJson(
                event.getDataObjectDeserializer().getRawJson(), PaymentIntent.class
        );

        String idStripe = intent.getId();

        Pago pago = pagoRepo.findByIdStripe(idStripe);

        if (pago == null) {
            System.err.println("Pago no encontrado para idStripe: " + idStripe);
            return;
        }

        if (pago.getEstado().equals("REALIZADO")) {
            return;
        }

        pago.setEstado("REALIZADO");
        pagoRepo.save(pago);

        System.out.println("Pago confirmado y viaje actualizado exitosamente.");
    }

    private void procesarPaymentFailed(Event event) {

        PaymentIntent intent = ApiResource.GSON.fromJson(
                event.getDataObjectDeserializer().getRawJson(), PaymentIntent.class
        );

        String idStripe = intent.getId();
        Pago pago = pagoRepo.findByIdStripe(idStripe);

        if (pago == null) return;

        pago.setEstado("FALLIDO");
        pagoRepo.save(pago);
    }
}
