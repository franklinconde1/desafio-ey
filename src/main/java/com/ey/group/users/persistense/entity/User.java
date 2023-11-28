package com.ey.group.users.persistense.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "users")
public class User {

    @Id
    private UUID id = UUID.randomUUID();

    private String name;
    @NotNull
    @NotBlank(message = "Email Requerido")
    @Email(message = "Formato de email invalido")
    private String email;
    private String password;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private List<Phone> phones;

}
