package com.splitwise.mapper;


import com.splitwise.dto.request.expense.ExpenseRequestDTO;
import com.splitwise.dto.response.ExpenseResponseDTO;
import com.splitwise.dto.response.GroupExpenseResponseDTO;
import com.splitwise.dto.response.SplitResponseDTO;
import com.splitwise.dto.response.UserResponseDto;
import com.splitwise.entity.Expense;
import com.splitwise.entity.Group;
import com.splitwise.entity.Split;
import com.splitwise.entity.User;
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


}
