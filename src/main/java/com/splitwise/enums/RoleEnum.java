package com.splitwise.enums;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

import static com.splitwise.enums.Permissions.*;

@RequiredArgsConstructor
public enum RoleEnum {

    ROLE_USER(Collections.emptySet()),
    ROLE_GROUP_ADMIN(
            Set.of(
                    GROUP_READ_EXPENSE,
                    GROUP_ADD_EXPENSE,

                    GROUP_REMOVE_USER,
                    GROUP_ADD_USER,
                    GROUP_READ_USERS,
                    GROUP_DELETE
            )
    ),

    ROLE_GROUP_USER(
            Set.of(
                    GROUP_READ_EXPENSE,
                    GROUP_ADD_EXPENSE,
                    GROUP_READ_USERS
            )
    );



    @Getter
    @Setter
    private String objectId;

    @Getter
    private final Set<Permissions> permissions;


    public String withObjectId(String objectId) {
        return this.name() + "_" + objectId;
    }




    public Set<String> getRolesSet(){
        var roles = getPermissions()
                .stream()
                .map(permission -> permission.withObjectId(objectId))
                .collect(Collectors.toSet());
        roles.add(withObjectId(objectId));

        return roles;
    }


}



