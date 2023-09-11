package com.splitwise.dto.request.expense;

import lombok.*;

import java.util.List;
import java.util.UUID;


@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupExpenseRequestDTO extends ExpenseRequestDTO{
    private List<String> groupList;
}
