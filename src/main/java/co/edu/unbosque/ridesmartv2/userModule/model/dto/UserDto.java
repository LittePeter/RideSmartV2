package co.edu.unbosque.ridesmartv2.userModule.model.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private String mail;
    private String name;
    private String password;
    private String role;
    private double balance;
    private int points;
}
