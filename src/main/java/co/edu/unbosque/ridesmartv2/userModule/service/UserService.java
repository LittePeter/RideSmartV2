package co.edu.unbosque.ridesmartv2.userModule.service;

import co.edu.unbosque.ridesmartv2.userModule.model.dto.UserDto;

import java.util.List;
import java.util.Optional;
/**
 * Define el contrato de servicios para la gestión de usuarios en el sistema.
 * <p>
 * Incluye operaciones de creación, consulta, actualización, eliminación y activación
 * de cuentas de usuario.
 * </p>
 */
public interface UserService {
    /**
     * Crea un nuevo usuario en el sistema.
     *
     * @param userDto los datos del usuario a crear.
     * @return el DTO del usuario creado.
     */
    UserDto create(UserDto userDto);
    /**
     * Busca un usuario por su identificador único.
     *
     * @param userId el ID del usuario.
     * @return un Optional que contiene el DTO del usuario, o vacío si no existe.
     */
    Optional<UserDto> findById(String userId);
    /**
     * Actualiza la información de un usuario existente.
     *
     * @param userDto los datos actualizados del usuario.
     * @return el DTO del usuario actualizado.
     */
    UserDto update(UserDto userDto);
    /**
     * Elimina un usuario del sistema por su ID.
     *
     * @param id el ID del usuario a eliminar.
     * @throws UserNotFoundException si el usuario no existe.
     */
    void delete(String id);
    /**
     * Obtiene la lista de todos los usuarios del sistema.
     *
     * @return una lista de DTOs de usuarios.
     */
    List<UserDto> getAllUserDtoList();
    /**
     * Activa la cuenta de un usuario, cambiando su estado de {@code PENDING} a {@code ACTIVE}.
     *
     * @param identification el ID del usuario a activar.
     */
    void activateUser(String identification);
}
