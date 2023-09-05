package com.splitwise.dto.request.expense;

import com.splitwise.enums.ExpenseCategory;
import com.splitwise.enums.ExpenseType;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ExpenseRequestDTO {



    @NotNull(message = "Payer ID is missing")
    private UUID payerId;

    @NotNull(message = "Expense type is missing")
    @Enumerated(EnumType.STRING)
    private ExpenseType expenseType;

    @NotNull(message = "Expense category is missing")
    @Enumerated(EnumType.STRING)
    private ExpenseCategory category;

    private String desc;

    @NotNull(message = "Amount is missing")
    @DecimalMin(value = "0.01", message = "Amount must be greater than or equal to 0.01")
    private Double amount;

    private Participants participants;
}
