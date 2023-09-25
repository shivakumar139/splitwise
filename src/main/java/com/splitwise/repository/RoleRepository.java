package com.splitwise.repository;

import com.splitwise.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Roles, String> {
    boolean existsByRole(String role);
}
