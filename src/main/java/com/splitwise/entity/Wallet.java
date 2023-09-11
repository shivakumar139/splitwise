package com.splitwise.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "wallet")
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @OneToOne
    @JoinColumn(name = "fk_user_id")
    private User userId;

    @NotEmpty(message = "Owes User id is missing")
    private Double owes;

    @NotEmpty(message = "Owed User id is missing")
    private Double owed;

    @NotEmpty(message = "Amount User id is missing")
    private Double amount;

}