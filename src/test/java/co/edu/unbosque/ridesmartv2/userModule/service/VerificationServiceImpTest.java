package co.edu.unbosque.ridesmartv2.userModule.service;

import co.edu.unbosque.ridesmartv2.config.ModelMapper;
import co.edu.unbosque.ridesmartv2.userModule.model.dto.VerificationTokenDto;
import co.edu.unbosque.ridesmartv2.userModule.model.entity.VerificationToken;
import co.edu.unbosque.ridesmartv2.userModule.model.persistence.VerificationTokenRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class VerificationServiceImpTest {


    @Mock
    private VerificationTokenRepo verificationTokenRepo;

    @Mock
    private ModelMapper mapper;

    @InjectMocks
    private VerificationServiceImp verificationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void getToken_returnsMappedDto_whenTokenExists() {

        String token = "abc123";

        VerificationToken tokenEntity = new VerificationToken();
        tokenEntity.setToken(token);

        VerificationTokenDto tokenDto = new VerificationTokenDto();
        tokenDto.setToken(token);

        when(verificationTokenRepo.findByToken(token)).thenReturn(tokenEntity);
        when(mapper.map(tokenEntity, VerificationTokenDto.class)).thenReturn(tokenDto);

        Optional<VerificationTokenDto> result = verificationService.getToken(token);

        assertTrue(result.isPresent());
        assertEquals(token, result.get().getToken());
        verify(verificationTokenRepo, times(1)).findByToken(token);
        verify(mapper, times(1)).map(tokenEntity, VerificationTokenDto.class);
    }

    @Test
    void getToken_returnsEmptyOptional_whenTokenDoesNotExist() {

        String token = "nope";

        when(verificationTokenRepo.findByToken(token)).thenReturn(null);

        Optional<VerificationTokenDto> result = verificationService.getToken(token);


        assertFalse(result.isPresent());
        verify(verificationTokenRepo, times(1)).findByToken(token);
        verify(mapper, never()).map(any(), any());
    }

    @Test
    void consumeToken_deletesTokenSuccessfully() {
        String token = "delete-me";
        verificationService.consumeToken(token);
        verify(verificationTokenRepo, times(1)).deleteByToken(token);
    }
}