package co.edu.unbosque.ridesmartv2.userModule.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
/**
 * Excepción lanzada cuando se intenta acceder a un usuario que no existe en el sistema.
 * <p>
 * Esta excepción se mapea a un código de estado HTTP 404 (Not Found).
 * </p>
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "User Not Existent")
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String id) {
        super("User with id " + id + " not found");
    }
}
