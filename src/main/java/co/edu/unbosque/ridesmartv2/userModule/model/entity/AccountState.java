package co.edu.unbosque.ridesmartv2.userModule.model.entity;
/**
 * Estados posibles de una cuenta de usuario en el sistema.
 * <ul>
 *   <li>{@code PENDING}: Cuenta recién creada, pendiente de verificación.</li>
 *   <li>{@code ACTIVE}: Cuenta verificada y operativa.</li>
 *   <li>{@code INACTIVE}: Cuenta temporalmente inactiva.</li>
 *   <li>{@code BLOCKED}: Cuenta bloqueada por administración.</li>
 *   <li>{@code SUSPENDED}: Cuenta suspendida (por ejemplo, por mal uso).</li>
 * </ul>
 */
public enum AccountState {
    PENDING,
    ACTIVE,
    INACTIVE,
    BLOCKED,
    SUSPENDED
}
