package co.edu.unbosque.ridesmartv2.userModule.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name ="biker")
public class User {
    @Id
    @Column(unique=true, nullable = false)
    private String identification;
    @Column(unique=true, nullable = false)
    private String mail;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String role;
    @Column (nullable = false)
    @Enumerated(EnumType.STRING)
    private AccountState status;
    @Column
    private double balance;
    @Column
    private int points;

}
