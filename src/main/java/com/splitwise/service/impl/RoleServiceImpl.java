package com.splitwise.service.impl;

import com.splitwise.entity.Roles;
import com.splitwise.entity.User;
import com.splitwise.enums.RoleEnum;
import com.splitwise.repository.RoleRepository;
import com.splitwise.service.RoleService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Set<Roles> createRole(RoleEnum role, String id) {

        role.setObjectId(id);
        return role.getRolesSet().stream().map(r -> Roles.builder().role(r).build()).collect(Collectors.toSet());

    }

    @Override
    public User removeRole(User user, String id) {

        // remove that role from roles table

        Set<Roles> roles = user.getRoles().stream().filter(role -> role.getRole().endsWith(id)).collect(Collectors.toSet());

        roleRepository.deleteAllById(
                roles.stream()
                .map(Roles::getId)
                .collect(Collectors.toList()));

        user.getRoles().removeAll(roles);

        return user;
    }

    @Override
    public boolean hasPermission(String permission) {
        return roleRepository.existsByRole(permission);
    }
}
