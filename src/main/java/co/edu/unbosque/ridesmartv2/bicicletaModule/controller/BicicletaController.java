package co.edu.unbosque.ridesmartv2.bicicletaModule.controller;

import co.edu.unbosque.ridesmartv2.bicicletaModule.model.dto.BicicletaDTO;
import co.edu.unbosque.ridesmartv2.bicicletaModule.service.BicicletaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bicicletas")
public class BicicletaController {

    @Autowired
    private BicicletaService bicicletaService;

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
