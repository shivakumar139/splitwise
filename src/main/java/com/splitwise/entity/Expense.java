package com.splitwise.entity;

import com.splitwise.enums.ExpenseCategory;
import com.splitwise.enums.ExpenseType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "expense")
@Builder
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotEmpty(message = "Expense type is missing")
    @Enumerated(EnumType.STRING)
    private ExpenseType expenseType;

    @NotEmpty(message = "Category is missing")
    @Enumerated(EnumType.STRING)
    private ExpenseCategory category;

    private String description;
    @NotEmpty(message = "Amount is missing")
    private Double amount;
    private LocalDateTime createdAt = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "fk_payer_id")
    private User payer;


//    @ManyToMany
//    @JoinTable(
//            name = "expense_group_association",
//            joinColumns = @JoinColumn(name = "fk_expense_id"),
//            inverseJoinColumns = @JoinColumn(name = "fk_group_id")
//    )
//    private List<Group> groups;
}