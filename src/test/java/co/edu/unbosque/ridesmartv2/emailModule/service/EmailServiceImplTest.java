package co.edu.unbosque.ridesmartv2.emailModule.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class EmailServiceImplTest {

    @Mock
    private JavaMailSender mailSender;

    @InjectMocks
    private EmailServiceImpl emailService;

    @Test
    void sendMail_shouldSendEmailWithCorrectParameters() {

        // Datos de prueba
        String to = "test@example.com";
        String subject = "Hola!";
        String body = "Este es un correo de prueba.";

        // Act
        emailService.sendMail(to, subject, body);

        // Capturamos el mensaje que se envió
        ArgumentCaptor<SimpleMailMessage> captor = ArgumentCaptor.forClass(SimpleMailMessage.class);
        verify(mailSender, times(1)).send(captor.capture());

        SimpleMailMessage sentMsg = captor.getValue();

        // Assert
        assertNotNull(sentMsg);
        assertArrayEquals(new String[]{to}, sentMsg.getTo());
        assertEquals(subject, sentMsg.getSubject());
        assertEquals(body, sentMsg.getText());
    }
}