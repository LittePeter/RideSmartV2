package co.edu.unbosque.ridesmartv2.estacionModule.service;

import co.edu.unbosque.ridesmartv2.estacionModule.model.dto.EstacionDTO;

import java.util.List;
/**
 * Define el contrato de servicios para la gestión de estaciones en el sistema RideSmart.
 */
public interface InterfaceEstacionService {
    /**
     * Crea una nueva estación en el sistema.
     *
     * @param estacionDTO los datos de la estación a crear.
     */
    void crearEstacion(EstacionDTO estacionDTO);
    /**
     * Obtiene una estación por su nombre.
     *
     * @param nombre el nombre de la estación.
     * @return un {@link EstacionDTO} con los datos de la estación.
     */
    EstacionDTO obtenerEstacion(String nombre);
    /**
     * Obtiene todas las estaciones de una categoría específica.
     *
     * @param Categoria la categoría de las estaciones (por ejemplo, "URBANA", "RURAL").
     * @return una lista de {@link EstacionDTO}.
     */
    List<EstacionDTO> obtenerEstacionPorCategoria(String Categoria);
    /**
     * Obtiene todas las estaciones con un estado específico.
     *
     * @param estado el estado de la estación (por ejemplo, "DISPONIBLE", "NO DISPONIBLE").
     * @return una lista de {@link EstacionDTO}.
     */
    List<EstacionDTO> obtenerEstacionPorEstado(String estado);
    /**
     * Cambia el estado de una estación a "DISPONIBLE".
     *
     * @param idEstacion el ID de la estación a habilitar.
     */
    void habilitarEstacion(String idEstacion);
    /**
     * Cambia el estado de una estación a "NO DISPONIBLE".
     *
     * @param idEstacion el ID de la estación a deshabilitar.
     */
    void desHabilitarEstacion(String idEstacion);
    /**
     * Genera una orden de redistribución de bicicletas si la estación está vacía.
     *
     * @param idEstacion el ID de la estación a verificar.
     */
    void generarOrdenRedistribucion (String idEstacion);
}
