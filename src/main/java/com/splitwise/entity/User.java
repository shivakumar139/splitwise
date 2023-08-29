package com.splitwise.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.UUID;

@Entity
@Table(name = "user")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotEmpty(message = "Name is missing")
    private String name;

    @NotEmpty(message = "Email is missing")
    @Email(message = "Invalid email")
    @Column(unique = true)
    private String email;

    @NotEmpty(message = "Mobile is missing")
    @Pattern(regexp = "^[6-9]\\d{9}$", message = "Invalid mobile number")
    private String mobile;

    @NotEmpty(message = "password is missing")
    @Size(min = 7, max = 30, message = "Password length should in between 7 to 30")
    private String password;

}