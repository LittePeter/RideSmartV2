package co.edu.unbosque.ridesmartv2.userModule.model.entity;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
/**
 * Token de verificación temporal asociado a un usuario durante el registro.
 * <p>
 * Se utiliza para confirmar la dirección de correo electrónico del usuario.
 * Cada token tiene una expiración de 10 minutos desde su creación.
 * </p>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name ="VerificationToken")
public class VerificationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String token;
    private String userId;
    private LocalDateTime expiration;
}
