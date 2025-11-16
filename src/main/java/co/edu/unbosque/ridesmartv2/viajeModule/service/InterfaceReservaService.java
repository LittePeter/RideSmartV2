package co.edu.unbosque.ridesmartv2.viajeModule.service;

import co.edu.unbosque.ridesmartv2.viajeModule.model.dto.ReservaDTO;

import java.util.List;
/**
 * Define el contrato de servicios para la gestión de reservas en el sistema RideSmart.
 */
public interface InterfaceReservaService {
    /**
     * Crea una nueva reserva para un usuario en una estación.
     *
     * @param reservaDTO los datos de la reserva a crear.
     * @return {@code true} si la reserva se crea exitosamente.
     */
    boolean createReserva(ReservaDTO reservaDTO);
    /**
     * Obtiene todas las reservas del sistema.
     *
     * @return una lista de {@link ReservaDTO}.
     */
    List<ReservaDTO> getReservas();
    /**
     * Obtiene las reservas asociadas a un usuario.
     *
     * @param idUsuario la identificación del usuario.
     * @return una lista de {@link ReservaDTO}.
     */
    List<ReservaDTO> getReservasByUsuario(String idUsuario);
    /**
     * Obtiene las reservas asociadas a una bicicleta.
     *
     * @param idBicicleta el ID de la bicicleta.
     * @return una lista de {@link ReservaDTO}.
     */
    List<ReservaDTO> getReservasByBicicleta(long idBicicleta);
    /**
     * Obtiene las reservas asociadas a una estación.
     *
     * @param idEstacion el ID de la estación.
     * @return una lista de {@link ReservaDTO}.
     */
    List<ReservaDTO> getReservasByEstacion(String idEstacion);
    /**
     * Obtiene una reserva por su ID.
     *
     * @param idReserva el ID de la reserva.
     * @return un {@link ReservaDTO} con los datos de la reserva.
     */
    ReservaDTO getReserva(long idReserva);
    /**
     * Cancela una reserva, cambiando su estado a "CANCELADA".
     *
     * @param idReserva el ID de la reserva a cancelar.
     * @return {@code true} si la operación es exitosa.
     */
    boolean cancelarReserva(long idReserva);
    /**
     * Marca una reserva como "CUMPLIDA" (cuando se inicia el viaje).
     *
     * @param idReserva el ID de la reserva.
     * @return {@code true} si la operación es exitosa.
     */
    boolean cumplirReserva(long idReserva);
    /**
     * Marca una reserva como "EXPIRADA" (cuando no se inicia a tiempo).
     *
     * @param idReserva el ID de la reserva.
     * @return {@code true} si la operación es exitosa.
     */
    boolean expirarReserva(long idReserva);
}
