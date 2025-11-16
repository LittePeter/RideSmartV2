package co.edu.unbosque.ridesmartv2.userModule.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
/**
 * Excepción lanzada cuando se intenta crear un usuario con un correo electrónico
 * que ya está registrado en el sistema.
 * <p>
 * Esta excepción se mapea a un código de estado HTTP 409 (Conflict).
 * </p>
 */
@ResponseStatus(value = HttpStatus.CONFLICT, reason = "User Already Exists")
public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
