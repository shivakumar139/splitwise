package com.splitwise.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "split")
public class Split {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "fk_expense_id")
    private Expense expense;

    @OneToOne
    @JoinColumn(name = "fk_user_id")
    private User user;

    private Double amount;
}