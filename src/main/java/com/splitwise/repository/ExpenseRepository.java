package com.splitwise.repository;

import com.splitwise.entity.Expense;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ExpenseRepository extends JpaRepository<Expense, String> {

    List<Expense> findAllByPayerId(String payerId, Pageable pageable);

}
