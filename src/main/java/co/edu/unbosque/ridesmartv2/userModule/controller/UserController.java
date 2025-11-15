package co.edu.unbosque.ridesmartv2.userModule.controller;

import co.edu.unbosque.ridesmartv2.userModule.exception.UserNotFoundException;
import co.edu.unbosque.ridesmartv2.userModule.model.dto.UserDto;
import co.edu.unbosque.ridesmartv2.userModule.model.dto.VerificationTokenDto;
import co.edu.unbosque.ridesmartv2.userModule.service.UserService;
import co.edu.unbosque.ridesmartv2.userModule.service.VerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
/**
 * Controlador REST para la gestión de usuarios.
 * <p>
 * Expone los endpoints para el registro, consulta, actualización, eliminación
 * y verificación de cuentas de usuario.
 * </p>
 */

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private VerificationService verificationService;

    /**
     * Registra un nuevo usuario en el sistema.
     * <p>
     * El usuario se crea con estado {@code PENDING} y se envía un correo de verificación
     * de forma asíncrona. El cliente recibe el DTO del usuario inmediatamente.
     * </p>
     *
     * @param userDto los datos del nuevo usuario.
     * @return una respuesta con el DTO del usuario y el estado 201 (Created).
     */
    @PostMapping
    public ResponseEntity<UserDto> create(@RequestBody UserDto userDto) {
        UserDto newUser = userService.create(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }
    /**
     * Obtiene la información de un usuario por su ID.
     *
     * @param id el ID del usuario a buscar.
     * @return una respuesta con el DTO del usuario y el estado 200 (OK).
     * @throws UserNotFoundException si el usuario no existe.
     */
    @GetMapping
    public ResponseEntity<UserDto> getUser(@RequestParam String id) {
        Optional<UserDto> getUser = userService.findById(id);
        if (getUser.isEmpty()) throw new UserNotFoundException(id);
        return ResponseEntity.ok(getUser.get());
    }
    /**
     * Obtiene la lista de todos los usuarios del sistema.
     *
     * @return una respuesta con la lista de DTOs de usuarios.
     *         Si no hay usuarios, devuelve estado 204 (No Content).
     */
    @GetMapping("/list")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = userService.getAllUserDtoList();
        if (users.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(users);
    }
        /**
     * Actualiza la información de un usuario existente.
     * <p>
     * <b>Nota:</b> Este método actualmente sobrescribe el correo del DTO con el ID de la URL,
     * lo cual puede ser un error y debería revisarse.
     * </p>
     *
     * @param id el ID del usuario a actualizar (en la URL).
     * @param userDto los datos actualizados del usuario.
     * @return el DTO del usuario actualizado.
     */
    @PutMapping("/{id}")
    public UserDto update(@PathVariable String id, @RequestBody UserDto userDto) {
        userDto.setMail(id);
        return userService.update(userDto);
    }
    /**
     * Elimina un usuario del sistema por su ID.
     *
     * @param id el ID del usuario a eliminar.
     * @return una respuesta vacía con estado 200 (OK).
     * @throws UserNotFoundException si el usuario no existe.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        userService.delete(id);
        return ResponseEntity.ok().build();
    }
    /**
     * Verifica la cuenta de un usuario usando un token de verificación.
     * <p>
     * Valida que el token exista y no haya expirado (10 minutos).
     * Si es válido, activa la cuenta del usuario y consume el token.
     * </p>
     *
     * @param token el token de verificación enviado por correo.
     * @return un mensaje de éxito o un error 400 si el token es inválido o expiró.
     */
    @PutMapping("/verify")
    public ResponseEntity<String> confirm(@RequestParam String token) {
        Optional<VerificationTokenDto> opt = verificationService.getToken(token);

        if (opt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Token inválido");
        }
        if (opt.get().getExpiration().isBefore(LocalDateTime.now())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Token expirado");
        }
        userService.activateUser(opt.get().getUserId());
        verificationService.consumeToken(token);

        return ResponseEntity.ok("Cuenta confirmada exitosamente");
    }


}

