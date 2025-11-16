package co.edu.unbosque.ridesmartv2.pagoModule.service;

import co.edu.unbosque.ridesmartv2.config.ModelMapper;
import co.edu.unbosque.ridesmartv2.pagoModule.model.dto.PagoDTO;
import co.edu.unbosque.ridesmartv2.pagoModule.model.entity.Pago;
import co.edu.unbosque.ridesmartv2.pagoModule.model.persistence.PagoRepository;
import co.edu.unbosque.ridesmartv2.viajeModule.model.entity.Viaje;
import co.edu.unbosque.ridesmartv2.viajeModule.service.InterfaceViajeService;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
/**
 * Implementación del servicio de gestión de pagos.
 * <p>
 * Coordina la lógica de negocio para crear pagos, calcular impuestos,
 * convertir monedas y comunicarse con la API de Stripe.
 * </p>
 */
@Service
public class PagoService implements InterfacePagoService {

    @Autowired
    private PagoRepository pagoRepo;
    @Autowired
    private InterfaceViajeService viajeService;
    @Autowired
    private CurrencyService currencyService;
    @Autowired
    private ModelMapper mp;

    @Override
    public boolean crearPago(long idViaje) {

        Viaje viaje = viajeService.getEntityById(idViaje);

        Pago pago = new Pago();
        pago.setIdViaje(viaje);
        pago.setCosto(viaje.getCosto());
        double costoUSD = currencyService.convertirCopAUsd(viaje.getCosto());
        pago.setCostoUSD(costoUSD);
        double impuestos = viaje.getCosto() * 0.19;
        pago.setImpuestos(impuestos);
        pago.setImpuestosUSD(currencyService.convertirCopAUsd(impuestos));
        pago.setEstado("PENDIENTE");

        return pagoRepo.save(pago).equals(pago);
    }

    @Override
    public PagoDTO getPagoById(long idPago) {
        PagoDTO pagoDTO = mp.map(pagoRepo.findById(idPago), PagoDTO.class);
        return pagoDTO;
    }

    @Override
    public PagoDTO getPagoByViaje(long viaje) {
        PagoDTO pagoDTO = mp.map(pagoRepo.findByIdViaje_IdViaje(viaje), PagoDTO.class);
        return pagoDTO;
    }

    @Override
    public List<PagoDTO> getAllPagos() {
        List<PagoDTO> pagosDTO = mp.mapList(pagoRepo.findAll(), PagoDTO.class);
        return pagosDTO;
    }

    @Override
    public PagoDTO getPagoByIdStripe(String idStripe) {
        PagoDTO pagoDTO = mp.map(pagoRepo.findByIdStripe(idStripe), PagoDTO.class);
        return pagoDTO;
    }

    @Override
    public boolean completarPago(long idPago) throws StripeException {

        Pago pago = pagoRepo.findById(idPago);

        double totalCOP = pago.getCosto() + pago.getImpuestos();
        long valor = (long) totalCOP;

        PaymentIntent intent = solicitudPago(valor, pago.getIdViaje().getIdViaje());

        pago.setIdStripe(intent.getId());
        pago.setEstado("CREATED_INTENT");
        pago.setFecha(LocalDateTime.now());


        return pagoRepo.updateEstadoPago(idPago,pago.getEstado()).equals(pago);
    }

    public PaymentIntent solicitudPago(long valor, long idViaje) throws StripeException {

        PaymentIntentCreateParams params =
                PaymentIntentCreateParams.builder()
                        .setAmount(valor)
                        .setCurrency("cop")
                        .setDescription("Pago viaje " + idViaje)
                        .setAutomaticPaymentMethods(
                                PaymentIntentCreateParams.AutomaticPaymentMethods.builder()
                                        .setEnabled(true)
                                        .build()
                        )
                        .build();

        return PaymentIntent.create(params);
    }
}
