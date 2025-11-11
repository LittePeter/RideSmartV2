package co.edu.unbosque.ridesmartv2.viajeModule.controller;

import co.edu.unbosque.ridesmartv2.viajeModule.model.dto.InfoFinViajeDTO;
import co.edu.unbosque.ridesmartv2.viajeModule.model.dto.InfoInitViajeDTO;
import co.edu.unbosque.ridesmartv2.viajeModule.model.dto.ViajeDTO;
import co.edu.unbosque.ridesmartv2.viajeModule.service.ViajeServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/viajes")
public class ViajeController {

    @Autowired
    private ViajeServiceI viajeService;


    @PostMapping("/iniciar")
    public ResponseEntity<?> iniciarViaje(@RequestBody InfoInitViajeDTO info) {
        try {
            ViajeDTO viaje = viajeService.iniciarViaje(info);
            if (viaje == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("No se pudo iniciar el viaje. Verifica la reserva o el estado actual.");
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(viaje);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al iniciar el viaje: " + e.getMessage());
        }
    }

    @PutMapping("/finalizar")
    public ResponseEntity<?> finalizarViaje(@RequestBody InfoFinViajeDTO info) {
        try {
            viajeService.finalizarViaje(info);
            return ResponseEntity.ok("Viaje finalizado correctamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al finalizar el viaje: " + e.getMessage());
        }
    }

    @GetMapping("/{idViaje}")
    public ResponseEntity<?> obtenerViaje(@PathVariable long idViaje) {
        try {
            ViajeDTO viaje = viajeService.obtenerViaje(idViaje);
            if (viaje == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No se encontró el viaje con ID: " + idViaje);
            }
            return ResponseEntity.ok(viaje);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener el viaje: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> listarViajes() {
        try {
            List<ViajeDTO> viajes = viajeService.obtenerViajes();
            if (viajes.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
            return ResponseEntity.ok(viajes);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al listar los viajes: " + e.getMessage());
        }
    }
}
