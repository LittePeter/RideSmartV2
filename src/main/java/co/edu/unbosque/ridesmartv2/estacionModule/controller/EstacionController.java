package co.edu.unbosque.ridesmartv2.estacionModule.controller;

import co.edu.unbosque.ridesmartv2.estacionModule.model.dto.EstacionDTO;
import co.edu.unbosque.ridesmartv2.estacionModule.service.EstacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estaciones")
public class EstacionController {

    @Autowired
    private EstacionService estacionService;

    @PostMapping
    public ResponseEntity<?> crearEstacion(@RequestBody EstacionDTO estacionDTO) {
        try {
            estacionService.crearEstacion(estacionDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Estación creada correctamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al crear la estación: " + e.getMessage());
        }
    }

    @GetMapping("/{idEstacion}")
    public ResponseEntity<?> obtenerEstacion(@PathVariable String idEstacion) {
        try {
            EstacionDTO estacion = estacionService.obtenerEstacion(idEstacion);
            if (estacion == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No se encontró la estación con ID: " + idEstacion);
            }
            return ResponseEntity.ok(estacion);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener la estación: " + e.getMessage());
        }
    }


    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<?> obtenerEstacionesPorCategoria(@PathVariable String categoria) {
        try {
            List<EstacionDTO> estaciones = estacionService.obtenerEstacionPorCategoria(categoria);
            if (estaciones.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
            return ResponseEntity.ok(estaciones);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener estaciones por categoría: " + e.getMessage());
        }
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<?> obtenerEstacionesPorEstado(@PathVariable String estado) {
        try {
            List<EstacionDTO> estaciones = estacionService.obtenerEstacionPorEstado(estado);
            if (estaciones.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
            return ResponseEntity.ok(estaciones);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener estaciones por estado: " + e.getMessage());
        }
    }

    @PutMapping("/{idEstacion}/habilitar")
    public ResponseEntity<?> habilitarEstacion(@PathVariable String idEstacion) {
        try {
            estacionService.habilitarEstacion(idEstacion);
            return ResponseEntity.ok("Estación habilitada correctamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al habilitar la estación: " + e.getMessage());
        }
    }

    @PutMapping("/{idEstacion}/deshabilitar")
    public ResponseEntity<?> desHabilitarEstacion(@PathVariable String idEstacion) {
        try {
            estacionService.desHabilitarEstacion(idEstacion);
            return ResponseEntity.ok("Estación deshabilitada correctamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al deshabilitar la estación: " + e.getMessage());
        }
    }

}