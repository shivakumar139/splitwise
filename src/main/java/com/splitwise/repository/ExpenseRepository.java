package com.splitwise.repository;

import com.splitwise.entity.Expense;
import com.splitwise.entity.User;
import com.splitwise.enums.ExpenseCategory;
import com.splitwise.enums.ExpenseType;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, UUID> {



}
