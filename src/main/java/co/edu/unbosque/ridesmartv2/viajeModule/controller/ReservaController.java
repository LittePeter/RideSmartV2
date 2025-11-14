package co.edu.unbosque.ridesmartv2.viajeModule.controller;

import co.edu.unbosque.ridesmartv2.viajeModule.model.dto.ReservaDTO;
import co.edu.unbosque.ridesmartv2.viajeModule.service.InterfaceReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservas")
public class ReservaController {

    @Autowired
    private InterfaceReservaService reservaService;

    @PostMapping("/crear")
    public ResponseEntity<?> crearReserva(@RequestBody ReservaDTO reservaDTO) {
        boolean creada = reservaService.createReserva(reservaDTO);

        if (creada) {
            return ResponseEntity.status(201).body("Reserva creada correctamente");
        } else {
            return ResponseEntity.badRequest().body("No se pudo crear la reserva");
        }
    }

    @GetMapping
    public ResponseEntity<List<ReservaDTO>> obtenerReservas() {
        return ResponseEntity.ok(reservaService.getReservas());
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<ReservaDTO>> obtenerReservasPorUsuario(
            @PathVariable String idUsuario
    ) {
        return ResponseEntity.ok(reservaService.getReservasByUsuario(idUsuario));
    }

    @GetMapping("/bicicleta/{idBicicleta}")
    public ResponseEntity<List<ReservaDTO>> obtenerReservasPorBicicleta(
            @PathVariable long idBicicleta
    ) {
        return ResponseEntity.ok(reservaService.getReservasByBicicleta(idBicicleta));
    }

    @GetMapping("/estacion/{estacion}")
    public ResponseEntity<List<ReservaDTO>> obtenerReservasPorEstacion(
            @PathVariable String estacion
    ) {
        return ResponseEntity.ok(reservaService.getReservasByEstacion(estacion));
    }

    @GetMapping("/{idReserva}")
    public ResponseEntity<?> obtenerReserva(@PathVariable long idReserva) {

        ReservaDTO reserva = reservaService.getReserva(idReserva);

        if (reserva == null) {
            return ResponseEntity.status(404).body("Reserva no encontrada");
        }

        return ResponseEntity.ok(reserva);
    }
    @PutMapping("/{idReserva}/cancelar")
    public ResponseEntity<?> cancelarReserva(@PathVariable long idReserva) {

        boolean exito = reservaService.cancelarReserva(idReserva);

        if (exito) {
            return ResponseEntity.ok("Reserva cancelada correctamente");
        } else {
            return ResponseEntity.badRequest().body("No se pudo cancelar la reserva");
        }
    }

    @PutMapping("/{idReserva}/cumplir")
    public ResponseEntity<?> cumplirReserva(@PathVariable long idReserva) {

        boolean exito = reservaService.cumplirReserva(idReserva);

        if (exito) {
            return ResponseEntity.ok("Reserva marcada como cumplida");
        } else {
            return ResponseEntity.badRequest().body("No se pudo completar la operación");
        }
    }
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