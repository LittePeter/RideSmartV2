package co.edu.unbosque.ridesmartv2.viajeModule.controller;

import co.edu.unbosque.ridesmartv2.viajeModule.model.dto.InfoReservaDTO;
import co.edu.unbosque.ridesmartv2.viajeModule.model.dto.ReservaDTO;
import co.edu.unbosque.ridesmartv2.viajeModule.model.entity.Reserva;
import co.edu.unbosque.ridesmartv2.viajeModule.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reservas")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @PostMapping
    public ResponseEntity<?> crearReserva(@RequestBody InfoReservaDTO info) {
        try {
            ReservaDTO reserva = reservaService.crearReserva(info);
            return ResponseEntity.status(HttpStatus.CREATED).body(reserva);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al crear la reserva: " + e.getMessage());
        }
    }

    @PutMapping("/{idReserva}/cancelar")
    public ResponseEntity<?> cancelarReserva(@PathVariable long idReserva) {
        try {
            reservaService.cancelarReserva(idReserva);
            return ResponseEntity.ok("Reserva cancelada correctamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al cancelar la reserva: " + e.getMessage());
        }
    }

    @PutMapping("/{idReserva}/expirar")
    public ResponseEntity<?> expirarReserva(@PathVariable long idReserva) {
        try {
            reservaService.expirarReserva(idReserva);
            return ResponseEntity.ok("Reserva expirada correctamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al expirar la reserva: " + e.getMessage());
        }
    }

    @PutMapping("/{idReserva}/cumplir")
    public ResponseEntity<?> cumplirReserva(@PathVariable long idReserva) {
        try {
            reservaService.cumplirReserva(idReserva);
            return ResponseEntity.ok("Reserva marcada como cumplida.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al cumplir la reserva: " + e.getMessage());
        }
    }

    @GetMapping("/{idReserva}")
    public ResponseEntity<?> obtenerReserva(@PathVariable long idReserva) {
        try {
            ReservaDTO reserva = reservaService.obtenerReserva(idReserva);
            if (reserva == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No se encontró la reserva con ID: " + idReserva);
            }
            return ResponseEntity.ok(reserva);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener la reserva: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> obtenerReservas() {
        try {
            List<ReservaDTO> reservas = reservaService.obtenerReservas();
            if (reservas.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
            return ResponseEntity.ok(reservas);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al listar las reservas: " + e.getMessage());
        }
    }
}
