package co.edu.unbosque.ridesmartv2.pagoModule.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Slf4j
@Component
public class CurrencyClient {

    @Autowired
    private RestTemplate restTemplate;

    private static final String URL =
            "https://api.exchangerate.host/latest?base=COP&symbols=USD";


    public double getCopToUsdRate() {
        try {

            Map<String, Object> response =
                    restTemplate.getForObject(URL, Map.class);

            if (response == null || !response.containsKey("rates")) {
                log.warn("Respuesta inesperada desde ExchangeRate API: {}", response);
                throw new RuntimeException("Error obteniendo tasas de cambio");
            }

            Map<String, Double> rates =
                    (Map<String, Double>) response.get("rates");

            double rate = rates.get("USD");

            log.info("Tasa COP → USD obtenida: {}", rate);

            return rate;

        } catch (Exception e) {
            log.error("Error consultando tasa de cambio: {}", e.getMessage());
            throw new RuntimeException("Servicio de conversión no disponible");
        }
    }
}
