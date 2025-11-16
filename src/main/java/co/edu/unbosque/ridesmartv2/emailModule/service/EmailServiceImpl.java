package co.edu.unbosque.ridesmartv2.emailModule.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
/**
 * Implementación del servicio de envío de correos electrónicos.
 * <p>
 * Utiliza {@link org.springframework.mail.javamail.JavaMailSender} para enviar mensajes
 * de texto simples mediante el protocolo SMTP.
 * </p>
 */
@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    @Override
    public void sendMail(String to, String subject, String body) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(to);
        msg.setSubject(subject);
        msg.setText(body);

        mailSender.send(msg);
        System.out.println("Sent mail to: " + to);
    }
}
