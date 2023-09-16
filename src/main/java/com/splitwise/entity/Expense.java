package com.splitwise.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.splitwise.enums.ExpenseCategory;
import com.splitwise.enums.ExpenseType;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "expense")
@Builder

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotNull(message = "Expense type is missing")
    @Enumerated(EnumType.STRING)
    private ExpenseType expenseType;

    @NotNull(message = "Expense type is category")
    @Enumerated(EnumType.STRING)
    private ExpenseCategory category;

    private String description;

    @NotNull(message = "Amount is missing")
    @DecimalMin(value = "0.01", message = "Amount must be greater than or equal to 0.01")
    private Double amount;

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "fk_payer_id")
    private User payer;




    @JoinTable(
            name = "expense_group_association",
            joinColumns = @JoinColumn(name = "fk_expense_id"),
            inverseJoinColumns = @JoinColumn(name = "fk_group_id")
    )
    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.PERSIST
    )
    private List<Group> groups;

    @OneToMany(
            mappedBy = "expense",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    private List<Split> splits;

    @PrePersist
    public void setCreatedAt(){
        this.createdAt = LocalDateTime.now();
    }
}