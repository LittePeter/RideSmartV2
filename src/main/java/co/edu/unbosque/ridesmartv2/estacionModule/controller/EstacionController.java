package co.edu.unbosque.ridesmartv2.estacionModule.controller;

import co.edu.unbosque.ridesmartv2.estacionModule.model.dto.EstacionDTO;
import co.edu.unbosque.ridesmartv2.estacionModule.service.InterfaceEstacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para la gestión de estaciones.
 * <p>
 * Expone endpoints para crear, consultar, habilitar y deshabilitar estaciones.
 * </p>
 */
@RestController
@RequestMapping("/api/estaciones")
public class EstacionController {

    @Autowired
    private InterfaceEstacionService estacionService;

    /**
     * Crea una nueva estación en el sistema.
     *
     * @param estacionDTO los datos de la estación a crear.
     * @return un mensaje de éxito (201) o error (500) según el resultado.
     */
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

    /**
     * Obtiene los detalles de una estación por su ID.
     *
     * @param idEstacion el ID de la estación.
     * @return el {@link EstacionDTO} (200) o 404 si no se encuentra.
     */
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

    /**
     * Obtiene todas las estaciones de una categoría específica.
     *
     * @param categoria la categoría de las estaciones.
     * @return una lista de {@link EstacionDTO} o 204 si está vacía.
     */
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

    /**
     * Obtiene todas las estaciones con un estado específico.
     *
     * @param estado el estado de las estaciones.
     * @return una lista de {@link EstacionDTO} o 204 si está vacía.
     */
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

    /**
     * Habilita una estación cambiando su estado a "DISPONIBLE".
     *
     * @param idEstacion el ID de la estación a habilitar.
     * @return un mensaje de éxito o error.
     */
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

    /**
     * Deshabilita una estación cambiando su estado a "NO DISPONIBLE".
     *
     * @param idEstacion el ID de la estación a deshabilitar.
     * @return un mensaje de éxito o error.
     */
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