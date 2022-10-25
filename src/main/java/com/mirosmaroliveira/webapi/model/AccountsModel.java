package com.mirosmaroliveira.webapi.model;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;
@Data
@Entity
@Table(name ="accounts")
public class AccountsModel  implements Serializable {
    private static final long serialVersionID= 1L;

    @Id

    @NotBlank(message = "shoose a login name ")
    @Column(name = "login", nullable = false, unique = true)
    @Length(max = 45, min = 4, message = "login must be 4 and 45 caracteres")
    private String login;

    @Column(nullable = false, length = 45)
    @NotBlank(message = "Enter a password")
    private String password;

    @Column(nullable = false)
    @NotBlank(message = "e-mail can't be empty")
    @Email(message = "Enter a valid email")
    private String email;


    @Column(name = "created_time")
    private LocalDateTime created_time;

    //private BigInteger lastactive;
    @Column(name = "accessLevel")
     private byte accessLevel;

    //private String lastIP;
    //private byte lastServer;
}
