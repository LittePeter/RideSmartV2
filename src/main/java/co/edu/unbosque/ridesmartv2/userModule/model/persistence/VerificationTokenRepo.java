package co.edu.unbosque.ridesmartv2.userModule.model.persistence;

import co.edu.unbosque.ridesmartv2.userModule.model.entity.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationTokenRepo extends JpaRepository<VerificationToken,String> {
    VerificationToken findByToken(String token);

    void deleteByToken(String token);
}
