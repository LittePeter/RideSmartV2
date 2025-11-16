package co.edu.unbosque.ridesmartv2.userModule.model.persistence;

import co.edu.unbosque.ridesmartv2.userModule.model.entity.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * Repositorio de datos para la entidad {@link VerificationToken}.
 * <p>
 * Proporciona operaciones CRUD y métodos personalizados para buscar y eliminar
 * tokens de verificación por su valor de cadena.
 * </p>
 */
public interface VerificationTokenRepo extends JpaRepository<VerificationToken,String> {
    VerificationToken findByToken(String token);

    void deleteByToken(String token);
}
