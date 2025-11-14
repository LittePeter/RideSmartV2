package co.edu.unbosque.ridesmartv2.userModule.service;

import co.edu.unbosque.ridesmartv2.config.ModelMapper;
import co.edu.unbosque.ridesmartv2.userModule.model.dto.VerificationTokenDto;
import co.edu.unbosque.ridesmartv2.userModule.model.persistence.VerificationTokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class VerificationServiceImp implements VerificationService {
    @Autowired
    private VerificationTokenRepo verificationTokenRepo;
    @Autowired
    private ModelMapper mapper;

    @Transactional
    @Override
    public Optional<VerificationTokenDto> getToken(String token) {
        return Optional.ofNullable(mapper.map(verificationTokenRepo.findByToken(token), VerificationTokenDto.class));
    }

    @Transactional
    @Override
    public void consumeToken(String token) {
        verificationTokenRepo.deleteByToken(token);
    }

}
