package co.edu.unbosque.ridesmartv2.userModule.model.persistence;

import co.edu.unbosque.ridesmartv2.userModule.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User,String> {

}
