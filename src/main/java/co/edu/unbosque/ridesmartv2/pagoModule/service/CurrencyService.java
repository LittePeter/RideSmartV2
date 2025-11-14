package co.edu.unbosque.ridesmartv2.pagoModule.service;

import co.edu.unbosque.ridesmartv2.pagoModule.client.CurrencyClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CurrencyService {

    @Autowired
    private CurrencyClient currencyClient;
    private Double ultimaTasaCopUsd = 0.00025;

    public double getCopToUsdRate() {

        try {
            double tasa = currencyClient.getCopToUsdRate();
            ultimaTasaCopUsd = tasa;
            return tasa;
        } catch (Exception e) {
            log.warn("Fallo al llamar a la API de moneda. Usando tasa almacenada: {}", ultimaTasaCopUsd);
            return ultimaTasaCopUsd;
        }
    }

    public double convertirCopAUsd(double valorCop) {
        double tasa = getCopToUsdRate();
        return valorCop * tasa;
    }

    public double refrescarTasa() {
        ultimaTasaCopUsd = currencyClient.getCopToUsdRate();
        return ultimaTasaCopUsd;
    }

    public double getTasaLocal() {
        return ultimaTasaCopUsd;
    }
}
