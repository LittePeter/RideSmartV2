package co.edu.unbosque.ridesmartv2.viajeModule.controller;

import co.edu.unbosque.ridesmartv2.viajeModule.model.dto.ViajeDTO;
import co.edu.unbosque.ridesmartv2.viajeModule.service.InterfaceViajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/viajes")
public class ViajeController {

    @Autowired
    private InterfaceViajeService viajeService;

    @PostMapping("/iniciar/{idReserva}")
    public ResponseEntity<?> iniciarViaje(@PathVariable long idReserva) {

        boolean iniciado = viajeService.iniciarViaje(idReserva);

        if (iniciado) {
            return ResponseEntity.status(201).body("Viaje iniciado correctamente");
        } else {
            return ResponseEntity.badRequest().body("No fue posible iniciar el viaje");
        }
    }

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

    @GetMapping("/{idViaje}")
    public ResponseEntity<?> obtenerViaje(@PathVariable long idViaje) {

        ViajeDTO viaje = viajeService.obtenerViaje(idViaje);

        if (viaje == null) {
            return ResponseEntity.status(404).body("Viaje no encontrado");
        }

        return ResponseEntity.ok(viaje);
    }

    @GetMapping("/reserva/{idReserva}")
    public ResponseEntity<?> obtenerPorReserva(@PathVariable long idReserva) {

        ViajeDTO viaje = viajeService.obtenerViajePorReserva(idReserva);

        if (viaje == null) {
            return ResponseEntity.status(404).body("Viaje no encontrado para esa reserva");
        }

        return ResponseEntity.ok(viaje);
    }

    @GetMapping("/estacion-fin/{idEstacion}")
    public ResponseEntity<List<ViajeDTO>> obtenerPorEstacionFin(
            @PathVariable String idEstacion
    ) {
        return ResponseEntity.ok(viajeService.obtenerViajePorEstacionFin(idEstacion));
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<ViajeDTO>> obtenerPorTipoViaje(
            @PathVariable String tipo
    ) {
        return ResponseEntity.ok(viajeService.obtenerViajePorTipoViaje(tipo));
    }
}