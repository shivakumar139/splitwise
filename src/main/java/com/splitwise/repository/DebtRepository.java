package com.splitwise.repository;

import com.splitwise.entity.Debt;
import com.splitwise.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DebtRepository extends JpaRepository<Debt, String> {

    Optional<Debt> findByPayerAndPayee(User payer, User payee);
    boolean existsByPayerAndPayee(User payer, User payee);
    void deleteByPayerAndPayee(User payer, User payee);

    @Query("SELECT d FROM Debt d WHERE d.payer = :user OR d.payee = :user")
    List<Debt> findByPayerOrPayee(User user);

    List<Debt> findByPayer(User user);
    List<Debt> findByPayee(User user);

}
