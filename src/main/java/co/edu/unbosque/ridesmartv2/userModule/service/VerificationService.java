package co.edu.unbosque.ridesmartv2.userModule.service;

import co.edu.unbosque.ridesmartv2.userModule.model.dto.VerificationTokenDto;

import java.util.Optional;
/**
 * Define el contrato para la gestión de tokens de verificación de cuenta.
 */
public interface VerificationService {
    /**
     * Obtiene un token de verificación por su valor de cadena.
     *
     * @param token el valor del token.
     * @return un Optional que contiene el DTO del token, o vacío si no existe o ha expirado.
     */
    Optional<VerificationTokenDto> getToken(String token);
    /**
     * Consume (elimina) un token de verificación tras su uso.
     *
     * @param token el valor del token a consumir.
     */
    void consumeToken(String token);

}
