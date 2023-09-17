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
    private String id;

    @ManyToOne
    @JoinColumn(name = "fk_payer_id")
    private User payer;

    @ManyToOne
    @JoinColumn(name = "fk_payee_id")
    private User payee;

    @NotEmpty(message = "Amount is missing")
    private Double amount;
}