package co.edu.unbosque.ridesmartv2.userModule.model.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VerificationTokenDto {
    private Long id;
    private String token;
    private Long userId;
    private LocalDateTime expiration;
}
