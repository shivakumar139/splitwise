package com.splitwise.repository;

import com.splitwise.entity.Expense;
import com.splitwise.entity.Split;
import com.splitwise.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SplitRepository extends JpaRepository<Split, String> {
    boolean existsByExpenseAndUser(Expense expense, User user);
}
