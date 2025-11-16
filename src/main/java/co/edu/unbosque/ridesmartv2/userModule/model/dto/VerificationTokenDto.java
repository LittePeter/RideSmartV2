package co.edu.unbosque.ridesmartv2.userModule.model.dto;

import lombok.*;

import java.time.LocalDateTime;
/**
 * Objeto de transferencia de datos (DTO) para la entidad {@link VerificationToken}.
 * <p>
 * Se utiliza internamente para operaciones de verificación de cuenta.
 * </p>
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VerificationTokenDto {
    private Long id;
    private String token;
    private String userId;
    private LocalDateTime expiration;
}
