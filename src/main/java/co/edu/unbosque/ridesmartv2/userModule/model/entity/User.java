package co.edu.unbosque.ridesmartv2.userModule.model.entity;
/**
 * Representa a un usuario del sistema RideSmart.
 * <p>
 * Esta entidad se mapea a la tabla {@code biker} en la base de datos.
 * El identificador único es el campo {@code identification} (tipo long).
 * </p>
 */
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * Representa a un usuario del sistema RideSmart.
 * <p>
 * Esta entidad se mapea a la tabla {@code biker} en la base de datos.
 * El identificador único es el campo {@code identification} (tipo long).
 * </p>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name ="biker")
public class User {
    @Id
    @Column(unique=true, nullable = false)
    private String identification;
    @Column(unique=true, nullable = false)
    private String mail;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String role;
    @Column (nullable = false)
    @Enumerated(EnumType.STRING)
    private AccountState status;
    @Column
    private double balance;
    @Column
    private int points;

}
