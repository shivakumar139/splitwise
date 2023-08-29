package com.splitwise.dto.request.expense;

import com.splitwise.enums.ExpenseCategory;
import com.splitwise.enums.ExpenseType;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ExpenseRequestDTO {
    private UUID payerId;
    private ExpenseType expenseType;
    private ExpenseCategory category;
    private String desc;
    private Double amount;
}
