package co.edu.unbosque.ridesmartv2.bicicletaModule.controller;

import co.edu.unbosque.ridesmartv2.bicicletaModule.model.dto.BicicletaDTO;
import co.edu.unbosque.ridesmartv2.bicicletaModule.service.InterfaceBiciService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para la gestión de bicicletas.
 * <p>
 * Expone endpoints para crear, consultar, reubicar, bloquear/desbloquear,
 * habilitar/inhabilitar, usar y recargar bicicletas.
 * </p>
 */
@RestController
@RequestMapping("/api/bicicletas")
public class BicicletaController {

    @Autowired
    private InterfaceBiciService bicicletaService;

    /**
     * Crea una nueva bicicleta en el sistema.
     *
     * @param biciDTO los datos de la bicicleta a crear.
     * @return un mensaje de éxito (201) o error (500) según el resultado.
     */
    @PostMapping
    public ResponseEntity<?> crearBicicleta(@RequestBody BicicletaDTO biciDTO) {
        try {
            bicicletaService.crearBicicleta(biciDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Bicicleta creada correctamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al crear la bicicleta: " + e.getMessage());
        }
    }

    /**
     * Obtiene la lista de todas las bicicletas del sistema.
     *
     * @return una lista de {@link BicicletaDTO} o 204 si está vacía.
     */
    @GetMapping
    public ResponseEntity<?> obtenerBicicletas() {
        try {
            List<BicicletaDTO> bicicletas = bicicletaService.obtenerBicicletas();
            if (bicicletas.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
            return ResponseEntity.ok(bicicletas);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener las bicicletas: " + e.getMessage());
        }
    }

    /**
     * Obtiene una bicicleta por su ID.
     *
     * @param id el ID de la bicicleta.
     * @return el {@link BicicletaDTO} (200) o 404 si no se encuentra.
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerBicicleta(@PathVariable long id) {
        try {
            BicicletaDTO bicicleta = bicicletaService.obtenerBicicleta(id);
            if (bicicleta == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No se encontró la bicicleta con ID: " + id);
            }
            return ResponseEntity.ok(bicicleta);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener la bicicleta: " + e.getMessage());
        }
    }

    /**
     * Obtiene todas las bicicletas con un estado específico.
     *
     * @param estado el estado de las bicicletas.
     * @return una lista de {@link BicicletaDTO} o 204 si está vacía.
     */
    @GetMapping("/estado/{estado}")
    public ResponseEntity<?> obtenerBicicletasPorEstado(@PathVariable String estado) {
        try {
            List<BicicletaDTO> bicicletas = bicicletaService.obtenerBicicletasPorEstado(estado);
            if (bicicletas.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
            return ResponseEntity.ok(bicicletas);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al filtrar bicicletas por estado: " + e.getMessage());
        }
    }

    /**
     * Obtiene todas las bicicletas asociadas a una estación específica.
     *
     * @param estacion el ID de la estación.
     * @return una lista de {@link BicicletaDTO} o 204 si está vacía.
     */
    @GetMapping("/estacion/{estacion}")
    public ResponseEntity<?> obtenerBicicletasPorEstacion(@PathVariable String estacion) {
        try {
            List<BicicletaDTO> bicicletas = bicicletaService.obtenerBicicletasPorEstacion(estacion);
            if (bicicletas.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
            return ResponseEntity.ok(bicicletas);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al filtrar bicicletas por estación: " + e.getMessage());
        }
    }

    /**
     * Reubica una bicicleta a una estación diferente.
     *
     * @param id       el ID de la bicicleta.
     * @param estacion el nuevo ID de la estación.
     * @return un mensaje de éxito o error.
     */
    @PutMapping("/{id}/reubicar/{estacion}")
    public ResponseEntity<?> reubicarBicicleta(@PathVariable long id, @PathVariable String estacion) {
        try {
            bicicletaService.reubicarBicicleta(id, estacion);
            return ResponseEntity.ok("Bicicleta reubicada correctamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al reubicar la bicicleta: " + e.getMessage());
        }
    }

    /**
     * Bloquea el candado de una bicicleta.
     *
     * @param id el ID de la bicicleta.
     * @return un mensaje de éxito o error.
     */
    @PutMapping("/{id}/bloquear")
    public ResponseEntity<?> bloquearBicicleta(@PathVariable long id) {
        try {
            bicicletaService.bloquearBicicleta(id);
            return ResponseEntity.ok("Bicicleta bloqueada correctamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al bloquear la bicicleta: " + e.getMessage());
        }
    }

    /**
     * Desbloquea el candado de una bicicleta.
     *
     * @param id el ID de la bicicleta.
     * @return un mensaje de éxito o error.
     */
    @PutMapping("/{id}/desbloquear")
    public ResponseEntity<?> desbloquearBicicleta(@PathVariable long id) {
        try {
            bicicletaService.desbloquearBicicleta(id);
            return ResponseEntity.ok("Bicicleta desbloqueada correctamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al desbloquear la bicicleta: " + e.getMessage());
        }
    }

    /**
     * Habilita una bicicleta (estado = "DISPONIBLE").
     *
     * @param id el ID de la bicicleta.
     * @return un mensaje de éxito o error.
     */
    @PutMapping("/{id}/habilitar")
    public ResponseEntity<?> habilitarBicicleta(@PathVariable long id) {
        try {
            bicicletaService.habilitarBicicleta(id);
            return ResponseEntity.ok("Bicicleta habilitada correctamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al habilitar la bicicleta: " + e.getMessage());
        }
    }

    /**
     * Inhabilita una bicicleta (estado = "NO DISPONIBLE").
     *
     * @param id el ID de la bicicleta.
     * @return un mensaje de éxito o error.
     */
    @PutMapping("/{id}/inhabilitar")
    public ResponseEntity<?> inhabilitarBicicleta(@PathVariable long id) {
        try {
            bicicletaService.inhabilitarBicicleta(id);
            return ResponseEntity.ok("Bicicleta inhabilitada correctamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al inhabilitar la bicicleta: " + e.getMessage());
        }
    }

    /**
     * Marca una bicicleta como "EN USO".
     *
     * @param id el ID de la bicicleta.
     * @return un mensaje de éxito o error.
     */
    @PutMapping("/{id}/usar")
    public ResponseEntity<?> usarBicicleta(@PathVariable long id) {
        try {
            bicicletaService.usarBicicleta(id);
            return ResponseEntity.ok("Bicicleta en uso.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al usar la bicicleta: " + e.getMessage());
        }
    }

    /**
     * Actualiza el nivel de batería de una bicicleta.
     *
     * @param id    el ID de la bicicleta.
     * @param nivel el nuevo nivel de batería (0-100).
     * @return un mensaje de éxito o error.
     */
    @PutMapping("/{id}/recargar/{nivel}")
    public ResponseEntity<?> recargarBicicleta(@PathVariable long id, @PathVariable int nivel) {
        try {
            bicicletaService.recargarBicicleta(id, nivel);
            return ResponseEntity.ok("Nivel de batería actualizado a " + nivel + "%.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al recargar la bicicleta: " + e.getMessage());
        }
    }
}
