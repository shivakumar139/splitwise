package com.splitwise.service;

import com.splitwise.entity.Roles;
import com.splitwise.entity.User;
import com.splitwise.enums.RoleEnum;

import java.util.Set;

public interface RoleService {
    Set<Roles> createRole(RoleEnum role, String id);
    User removeRole(User user, String id);

    boolean hasPermission(String permission);
}
