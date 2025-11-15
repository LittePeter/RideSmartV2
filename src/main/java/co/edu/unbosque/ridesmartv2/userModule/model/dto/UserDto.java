package co.edu.unbosque.ridesmartv2.userModule.model.dto;

import lombok.*;
/**
 * Objeto de transferencia de datos (DTO) para la entidad {@link co.edu.unbosque.ridesmartv2.userModule.model.entity.User}.
 * <p>
 * Se utiliza para encapsular los datos de un usuario en las comunicaciones
 * entre el cliente y el servidor, evitando exponer la entidad directamente.
 * </p>
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private String identification;
    private String mail;
    private String name;
    private String password;
    private String role;
    private double balance;
    private int points;
}
