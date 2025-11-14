package co.edu.unbosque.ridesmartv2.pagoModule.service;

import co.edu.unbosque.ridesmartv2.pagoModule.model.dto.PagoDTO;
import com.stripe.exception.StripeException;

import java.util.List;

public interface InterfacePagoService {

    boolean crearPago(long idViaje);
    PagoDTO getPagoById(long idPago);
    PagoDTO getPagoByViaje(long viaje);
    List<PagoDTO> getAllPagos();
    PagoDTO getPagoByIdStripe(String idStripe);
    boolean completarPago(long idPago) throws StripeException;
}
