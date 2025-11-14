package co.edu.unbosque.ridesmartv2.emailModule.listener;

import co.edu.unbosque.ridesmartv2.emailModule.service.EmailService;
import co.edu.unbosque.ridesmartv2.sharedEvents.UserCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailEventListener {
    @Autowired
    private final EmailService emailService;

    @Async
    @EventListener
    public void handleUserCreated(UserCreatedEvent event)
    {
        String verificationLink = "http://localhost:8080/api/users/verify?token="+event.token();
        String body = """
                Hola %s,
                
                Gracias por registrarte en RideSmart.
                Por favor verifica tu cuenta haciendo clic en el siguiente enlace:
                
                %s
                
                Si no fuiste tú, ignora este mensaje.
                """.formatted(event.name(), verificationLink);

        emailService.sendMail(event.mail(), "Verifica tu cuenta", body);
    }
}
