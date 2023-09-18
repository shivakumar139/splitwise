package com.splitwise.mapper;


import com.splitwise.dto.request.expense.ExpenseRequestDTO;
import com.splitwise.dto.response.*;
import com.splitwise.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;


@Mapper(componentModel = "spring")
public interface CustomMapper {


    UserResponseDto map(User user);
    User map(UserResponseDto userResponseDto);

    List<ExpenseResponseDTO> map(List<Expense> expenses);

    List<UserResponseDto> mapToUserDto(List<User> users);


    Expense map(ExpenseResponseDTO expenseResponseDTO);

    ExpenseResponseDTO map(Expense expense);

    @Mapping(source = "expenseRequestDTO.desc", target = "description")
    Expense map(ExpenseRequestDTO expenseRequestDTO);



    SplitResponseDTO map(Split split);
    SplitResponseDTO map(SplitResponseDTO splitResponseDTO);


    Group map(GroupExpenseResponseDTO groupExpenseResponseDTO);
    GroupExpenseResponseDTO map(Group group);


    // debts
    DebtResponseDTO map(Debt debt);

    List<DebtResponseDTO> mapToDebtDtoList(List<Debt> debts);


}
