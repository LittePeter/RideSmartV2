package co.edu.unbosque.ridesmartv2.viajeModule.controller;

import co.edu.unbosque.ridesmartv2.viajeModule.model.dto.ReservaDTO;
import co.edu.unbosque.ridesmartv2.viajeModule.service.InterfaceReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * Controlador REST para la gestión de reservas.
 * <p>
 * Expone endpoints para crear, consultar, cancelar y actualizar el estado de reservas.
 * </p>
 */
@RestController
@RequestMapping("/api/reservas")
public class ReservaController {

    @Autowired
    private InterfaceReservaService reservaService;
    /**
     * Controlador REST para la gestión de reservas.
     * <p>
     * Expone endpoints para crear, consultar, cancelar y actualizar el estado de reservas.
     * </p>
     */
    @PostMapping("/crear")
    public ResponseEntity<?> crearReserva(@RequestBody ReservaDTO reservaDTO) {
        boolean creada = reservaService.createReserva(reservaDTO);

        if (creada) {
            return ResponseEntity.status(201).body("Reserva creada correctamente");
        } else {
            return ResponseEntity.badRequest().body("No se pudo crear la reserva");
        }
    }
    /**
     * Obtiene todas las reservas del sistema.
     *
     * @return una lista de {@link ReservaDTO} (200) o 204 si está vacía.
     */
    @GetMapping
    public ResponseEntity<List<ReservaDTO>> obtenerReservas() {
        return ResponseEntity.ok(reservaService.getReservas());
    }
    /**
     * Obtiene las reservas de un usuario.
     *
     * @param idUsuario la identificación del usuario.
     * @return una lista de {@link ReservaDTO}.
     */
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<ReservaDTO>> obtenerReservasPorUsuario(
            @PathVariable String idUsuario
    ) {
        return ResponseEntity.ok(reservaService.getReservasByUsuario(idUsuario));
    }
    /**
     * Obtiene las reservas asociadas a una bicicleta.
     *
     * @param idBicicleta el ID de la bicicleta.
     * @return una lista de {@link ReservaDTO}.
     */
    @GetMapping("/bicicleta/{idBicicleta}")
    public ResponseEntity<List<ReservaDTO>> obtenerReservasPorBicicleta(
            @PathVariable long idBicicleta
    ) {
        return ResponseEntity.ok(reservaService.getReservasByBicicleta(idBicicleta));
    }
    /**
     * Obtiene las reservas asociadas a una estación.
     *
     * @param estacion el ID de la estación.
     * @return una lista de {@link ReservaDTO}.
     */
    @GetMapping("/estacion/{estacion}")
    public ResponseEntity<List<ReservaDTO>> obtenerReservasPorEstacion(
            @PathVariable String estacion
    ) {
        return ResponseEntity.ok(reservaService.getReservasByEstacion(estacion));
    }
    /**
     * Obtiene una reserva por su ID.
     *
     * @param idReserva el ID de la reserva.
     * @return el {@link ReservaDTO} (200) o 404 si no se encuentra.
     */
    @GetMapping("/{idReserva}")
    public ResponseEntity<?> obtenerReserva(@PathVariable long idReserva) {

        ReservaDTO reserva = reservaService.getReserva(idReserva);

        if (reserva == null) {
            return ResponseEntity.status(404).body("Reserva no encontrada");
        }

        return ResponseEntity.ok(reserva);
    }
    /**
     * Cancela una reserva.
     *
     * @param idReserva el ID de la reserva a cancelar.
     * @return un mensaje de éxito o error.
     */
    @PutMapping("/{idReserva}/cancelar")
    public ResponseEntity<?> cancelarReserva(@PathVariable long idReserva) {

        boolean exito = reservaService.cancelarReserva(idReserva);

        if (exito) {
            return ResponseEntity.ok("Reserva cancelada correctamente");
        } else {
            return ResponseEntity.badRequest().body("No se pudo cancelar la reserva");
        }
    }
    /**
     * Marca una reserva como "CUMPLIDA".
     *
     * @param idReserva el ID de la reserva.
     * @return un mensaje de éxito o error.
     */
    @PutMapping("/{idReserva}/cumplir")
    public ResponseEntity<?> cumplirReserva(@PathVariable long idReserva) {

        boolean exito = reservaService.cumplirReserva(idReserva);

        if (exito) {
            return ResponseEntity.ok("Reserva marcada como cumplida");
        } else {
            return ResponseEntity.badRequest().body("No se pudo completar la operación");
        }
    }
    /**
     * Marca una reserva como "EXPIRADA".
     *
     * @param idReserva el ID de la reserva.
     * @return un mensaje de éxito o error.
     */
    @PutMapping("/{idReserva}/expirar")
    public ResponseEntity<?> expirarReserva(@PathVariable long idReserva) {

        boolean exito = reservaService.expirarReserva(idReserva);

        if (exito) {
            return ResponseEntity.ok("Reserva expirada");
        } else {
            return ResponseEntity.badRequest().body("No se pudo expirar la reserva");
        }
    }
}