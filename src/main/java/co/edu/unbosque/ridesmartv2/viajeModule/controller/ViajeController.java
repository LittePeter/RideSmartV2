package co.edu.unbosque.ridesmartv2.viajeModule.controller;

import co.edu.unbosque.ridesmartv2.viajeModule.model.dto.ViajeDTO;
import co.edu.unbosque.ridesmartv2.viajeModule.service.InterfaceViajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * Controlador REST para la gestión de viajes.
 * <p>
 * Expone endpoints para iniciar, finalizar y consultar viajes.
 * </p>
 */
@RestController
@RequestMapping("/api/viajes")
public class ViajeController {

    @Autowired
    private InterfaceViajeService viajeService;
    /**
     * Inicia un viaje a partir de una reserva.
     *
     * @param idReserva el ID de la reserva.
     * @return un mensaje de éxito (201) o error (400) según el resultado.
     */
    @PostMapping("/iniciar/{idReserva}")
    public ResponseEntity<?> iniciarViaje(@PathVariable long idReserva) {

        boolean iniciado = viajeService.iniciarViaje(idReserva);

        if (iniciado) {
            return ResponseEntity.status(201).body("Viaje iniciado correctamente");
        } else {
            return ResponseEntity.badRequest().body("No fue posible iniciar el viaje");
        }
    }
    /**
     * Finaliza un viaje, especificando la estación de destino.
     *
     * @param idViaje el ID del viaje a finalizar.
     * @param estacionFin el ID de la estación donde termina el viaje.
     * @return un mensaje de éxito o error.
     */
    @PutMapping("/finalizar/{idViaje}")
    public ResponseEntity<?> finalizarViaje(
            @PathVariable long idViaje,
            @RequestParam String estacionFin
    ) {

        boolean finalizado = viajeService.finalizarViaje(idViaje, estacionFin);

        if (finalizado) {
            return ResponseEntity.ok("Viaje finalizado correctamente");
        } else {
            return ResponseEntity.badRequest().body("No se pudo finalizar el viaje");
        }
    }
    /**
     * Obtiene los detalles de un viaje por su ID.
     *
     * @param idViaje el ID del viaje.
     * @return el {@link ViajeDTO} (200) o un error 404 si no se encuentra.
     */
    @GetMapping("/{idViaje}")
    public ResponseEntity<?> obtenerViaje(@PathVariable long idViaje) {

        ViajeDTO viaje = viajeService.obtenerViaje(idViaje);

        if (viaje == null) {
            return ResponseEntity.status(404).body("Viaje no encontrado");
        }

        return ResponseEntity.ok(viaje);
    }
    /**
     * Obtiene el viaje asociado a una reserva.
     *
     * @param idReserva el ID de la reserva.
     * @return el {@link ViajeDTO} (200) o un error 404 si no existe.
     */
    @GetMapping("/reserva/{idReserva}")
    public ResponseEntity<?> obtenerPorReserva(@PathVariable long idReserva) {

        ViajeDTO viaje = viajeService.obtenerViajePorReserva(idReserva);

        if (viaje == null) {
            return ResponseEntity.status(404).body("Viaje no encontrado para esa reserva");
        }

        return ResponseEntity.ok(viaje);
    }
    /**
     * Obtiene todos los viajes que terminaron en una estación específica.
     *
     * @param idEstacion el ID de la estación de fin.
     * @return una lista de {@link ViajeDTO}.
     */
    @GetMapping("/estacion-fin/{idEstacion}")
    public ResponseEntity<List<ViajeDTO>> obtenerPorEstacionFin(
            @PathVariable String idEstacion
    ) {
        return ResponseEntity.ok(viajeService.obtenerViajePorEstacionFin(idEstacion));
    }
    /**
     * Obtiene todos los viajes de un tipo específico.
     *
     * @param tipo el tipo de viaje (por ejemplo, "LARGO").
     * @return una lista de {@link ViajeDTO}.
     */
    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<ViajeDTO>> obtenerPorTipoViaje(
            @PathVariable String tipo
    ) {
        return ResponseEntity.ok(viajeService.obtenerViajePorTipoViaje(tipo));
    }
}