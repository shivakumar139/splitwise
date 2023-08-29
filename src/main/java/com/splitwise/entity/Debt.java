package com.splitwise.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "debt")
public class Debt {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne
    @JoinColumn(name = "fk_payer_id")
    private User payerId;

    @OneToOne
    @JoinColumn(name = "fk_payee_id")
    private User payeeId;

    @NotEmpty(message = "Amount is missing")
    private Double amount;
}