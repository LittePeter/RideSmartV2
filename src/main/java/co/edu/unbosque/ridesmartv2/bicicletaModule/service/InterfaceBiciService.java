package co.edu.unbosque.ridesmartv2.bicicletaModule.service;

import co.edu.unbosque.ridesmartv2.bicicletaModule.model.dto.BicicletaDTO;

import java.util.List;
/**
 * Define el contrato de servicios para la gestión de bicicletas en el sistema RideSmart.
 */
public interface InterfaceBiciService {
    /**
     * Crea una nueva bicicleta en el sistema.
     *
     * @param biciDTO los datos de la bicicleta a crear.
     */
    void crearBicicleta(BicicletaDTO biciDTO);
    /**
     * Obtiene la lista de todas las bicicletas del sistema.
     *
     * @return una lista de {@link BicicletaDTO}.
     */
    List<BicicletaDTO> obtenerBicicletas();
    /**
     * Obtiene una bicicleta por su ID.
     *
     * @param id el ID de la bicicleta.
     * @return el {@link BicicletaDTO} de la bicicleta, o {@code null} si no existe.
     */
    BicicletaDTO obtenerBicicleta(long id);
    /**
     * Obtiene todas las bicicletas con un estado específico.
     *
     * @param estado el estado de las bicicletas (por ejemplo, "DISPONIBLE", "EN USO").
     * @return una lista de {@link BicicletaDTO}.
     */
    List<BicicletaDTO> obtenerBicicletasPorEstado(String estado);
    /**
     * Obtiene todas las bicicletas asociadas a una estación específica.
     *
     * @param estacion el ID de la estación.
     * @return una lista de {@link BicicletaDTO}.
     */
    List<BicicletaDTO> obtenerBicicletasPorEstacion(String estacion);
    /**
     * Cambia la estación de una bicicleta.
     *
     * @param idBicicleta el ID de la bicicleta.
     * @param estacion el nuevo ID de la estación.
     */
    void reubicarBicicleta(long idBicicleta, String estacion);
    /**
     * Bloquea el candado de una bicicleta.
     *
     * @param idBicicleta el ID de la bicicleta.
     */
    void bloquearBicicleta(long idBicicleta);
    /**
     * Desbloquea el candado de una bicicleta.
     *
     * @param idBicicleta el ID de la bicicleta.
     */
    void desbloquearBicicleta(long idBicicleta);
    /**
     * Cambia el estado de una bicicleta a "DISPONIBLE".
     *
     * @param idBicicleta el ID de la bicicleta.
     */
    void habilitarBicicleta(long idBicicleta);
    /**
     * Cambia el estado de una bicicleta a "NO DISPONIBLE".
     *
     * @param idBicicleta el ID de la bicicleta.
     */
    void inhabilitarBicicleta(long idBicicleta);
    /**
     * Cambia el estado de una bicicleta a "EN USO".
     *
     * @param idBicicleta el ID de la bicicleta.
     */
    void usarBicicleta(long idBicicleta);
    /**
     * Actualiza el nivel de batería de una bicicleta.
     *
     * @param idBicicleta el ID de la bicicleta.
     * @param newBateria el nuevo nivel de batería (0-100).
     */
    void recargarBicicleta(long idBicicleta, int newBateria);
}
