package co.edu.unbosque.ridesmartv2.pagoModule.controller;

import co.edu.unbosque.ridesmartv2.pagoModule.service.PagoWebhookService;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.model.Event;
import com.stripe.net.Webhook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/webhooks/stripe")
public class StripeController {

    @Value("${stripe.webhook.secret}")
    private String endpointSecret;

    @Autowired
    private PagoWebhookService webhookService;

    @PostMapping
    public ResponseEntity<String> recibirWebhook(
            @RequestBody String payload,
            @RequestHeader("Stripe-Signature") String signatureHeader) {

        Event event;

        try {
            event = Webhook.constructEvent(payload, signatureHeader, endpointSecret);
        } catch (SignatureVerificationException e) {
            return ResponseEntity.status(400).body("Firma inválida");
        }

        webhookService.procesarEvento(event);

        return ResponseEntity.ok("Webhook recibido");
    }
}
