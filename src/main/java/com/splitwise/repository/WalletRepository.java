package com.splitwise.repository;

import com.splitwise.entity.User;
import com.splitwise.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, String> {

    boolean existsByUser(User user);

    Wallet findByUserId(String userId);
}
