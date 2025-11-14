package co.edu.unbosque.ridesmartv2.emailModule.service;

public interface EmailService {
    void sendMail(String to, String subject, String body);
}
