package co.edu.unbosque.ridesmartv2.userModule.service;

import co.edu.unbosque.ridesmartv2.userModule.model.dto.VerificationTokenDto;

import java.util.Optional;

public interface VerificationService {
    Optional<VerificationTokenDto> getToken(String token);

    void consumeToken(String token);

}
