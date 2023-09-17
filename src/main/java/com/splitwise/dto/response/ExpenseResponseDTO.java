package com.splitwise.dto.response;

import com.splitwise.enums.ExpenseCategory;
import com.splitwise.enums.ExpenseType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExpenseResponseDTO {
    private ExpenseType expenseType;
    private ExpenseCategory category;

    private String description;
    private Double amount;

    private LocalDateTime createdAt;
    private UserResponseDto payer;


    private List<SplitResponseDTO> splits;

    private List<GroupExpenseResponseDTO> groups;
}
