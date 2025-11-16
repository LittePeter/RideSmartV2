package co.edu.unbosque.ridesmartv2.viajeModule.service;

import co.edu.unbosque.ridesmartv2.viajeModule.model.dto.ViajeDTO;
import co.edu.unbosque.ridesmartv2.viajeModule.model.entity.Viaje;

import java.util.List;
/**
 * Define el contrato de servicios para la gestión de viajes en el sistema RideSmart.
 */
public interface InterfaceViajeService {
    /**
     * Inicia un viaje a partir de una reserva existente.
     *
     * @param idReserva el ID de la reserva a partir de la cual se inicia el viaje.
     * @return {@code true} si el viaje se inicia correctamente, {@code false} si la reserva expira.
     */
    boolean iniciarViaje(long idReserva);
    /**
     * Obtiene los detalles de un viaje por su ID.
     *
     * @param idViaje el ID del viaje.
     * @return un {@link ViajeDTO} con los datos del viaje, o {@code null} si no existe.
     */
    ViajeDTO obtenerViaje(long idViaje);
    /**
     * Obtiene el viaje asociado a una reserva.
     *
     * @param idReserva el ID de la reserva.
     * @return un {@link ViajeDTO} con los datos del viaje.
     */
    ViajeDTO obtenerViajePorReserva(long idReserva);
    /**
     * Obtiene todos los viajes que terminaron en una estación específica.
     *
     * @param idEstacion el ID de la estación de fin.
     * @return una lista de {@link ViajeDTO}.
     */
    List<ViajeDTO> obtenerViajePorEstacionFin(String idEstacion);
    /**
     * Obtiene todos los viajes de un tipo específico (por ejemplo, "LARGO" o "ULT. MILLA").
     *
     * @param tipoViaje el tipo de viaje.
     * @return una lista de {@link ViajeDTO}.
     */
    List<ViajeDTO> obtenerViajePorTipoViaje(String tipoViaje);
    /**
     * Finaliza un viaje, calculando su duración, costo y actualizando el estado de la bicicleta.
     *
     * @param idViaje el ID del viaje a finalizar.
     * @param idEstacionFin el ID de la estación donde termina el viaje.
     * @return {@code true} si el viaje se finaliza correctamente.
     */
    boolean finalizarViaje(long idViaje, String idEstacionFin);
    /**
     * Obtiene la entidad {@link Viaje} por su ID (usado internamente por otros módulos como el de pagos).
     *
     * @param idViaje el ID del viaje.
     * @return la entidad {@link Viaje}, o {@code null} si no existe.
     */
    Viaje getEntityById(long idViaje);
}
