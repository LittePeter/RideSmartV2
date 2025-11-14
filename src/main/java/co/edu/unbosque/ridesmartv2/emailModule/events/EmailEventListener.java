package co.edu.unbosque.ridesmartv2.emailModule.events;

import co.edu.unbosque.ridesmartv2.emailModule.service.EmailService;
import co.edu.unbosque.ridesmartv2.userModule.USerCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailEventListener {
    @Autowired
    private final EmailService emailService;

    @EventListener
    public void handleUserCreated(USerCreatedEvent event)
    {
        String verificationLink = "http://localhost:8080/api/users/verify?token="+event.;
    }
}
