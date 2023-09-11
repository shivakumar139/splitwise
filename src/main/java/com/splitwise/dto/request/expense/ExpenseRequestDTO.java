package com.splitwise.dto.request.expense;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.splitwise.enums.ExpenseCategory;
import com.splitwise.enums.ExpenseType;

import com.splitwise.validator.annotations.NotBlankEnum;
import com.splitwise.validator.annotations.ValidExpenseType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor

@ValidExpenseType
public class ExpenseRequestDTO {

    @NotEmpty(message = "Payer ID is missing")
    private String payerId;

    @NotBlankEnum(enumClass = ExpenseType.class ,message = "Invalid Expense Type")
    @Enumerated(EnumType.STRING)
    private ExpenseType expenseType;

    @NotBlankEnum(enumClass = ExpenseCategory.class ,message = "Invalid category")
    @Enumerated(EnumType.STRING)
    private ExpenseCategory category;

    private String desc;

    @NotNull(message = "Amount is missing")
    @DecimalMin(value = "0.01", message = "Amount must be greater than or equal to 0.01")
    private Double amount;

    @Valid
    private Participants participants;
}
