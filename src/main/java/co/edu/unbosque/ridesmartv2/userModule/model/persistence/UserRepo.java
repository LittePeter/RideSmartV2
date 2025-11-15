package co.edu.unbosque.ridesmartv2.userModule.model.persistence;

import co.edu.unbosque.ridesmartv2.userModule.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * Repositorio de datos para la entidad {@link User}.
 * <p>
 * Proporciona operaciones CRUD estándar mediante Spring Data JPA.
 * La clave primaria es de tipo {@code Long}.
 * </p>
 */
@Repository
public interface UserRepo extends JpaRepository<User,String> {

}
