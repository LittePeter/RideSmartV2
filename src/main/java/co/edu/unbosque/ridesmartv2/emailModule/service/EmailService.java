package co.edu.unbosque.ridesmartv2.emailModule.service;
/**
 * Define el contrato para el envío de correos electrónicos en el sistema RideSmart.
 */
public interface EmailService {
    /**
     * Envía un correo electrónico al destinatario especificado.
     *
     * @param to el correo electrónico del destinatario.
     * @param subject el asunto del mensaje.
     * @param body el cuerpo del mensaje.
     */
    void sendMail(String to, String subject, String body);
}
