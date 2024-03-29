package com.splitwise.service.impl;

import com.splitwise.dto.request.GroupRequestDto;
import com.splitwise.dto.response.ApiResponse;
import com.splitwise.entity.Expense;
import com.splitwise.entity.Group;
import com.splitwise.entity.Roles;
import com.splitwise.entity.User;
import com.splitwise.enums.RoleEnum;
import com.splitwise.exception.CreatorAndDeletedAreSameException;
import com.splitwise.exception.GroupNotFoundException;
import com.splitwise.exception.UserIsAlreadyExistsInGroupException;
import com.splitwise.mapper.CustomMapper;
import com.splitwise.repository.GroupRepository;
import com.splitwise.service.GroupService;
import com.splitwise.service.RoleService;
import com.splitwise.service.UserService;
import com.sun.jdi.InternalException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Transactional
@Service
@Slf4j
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private CustomMapper customMapper;

    @Autowired
    private RoleService roleService;

    @Override
    public ApiResponse<Object> createGroup(GroupRequestDto groupRequestDto) {
        User createdUser = (User)userService.findUserById(groupRequestDto.getCreatedBy()).getData();


        String desc = groupRequestDto.getDescription();

        Group createdGroup = Group.builder()
                .name(groupRequestDto.getName())
                .createdBy(createdUser)
                .createdAt(LocalDateTime.now())
                .description(desc == null || desc.isBlank() || desc.isEmpty() ? "NA":  desc)
                .users(Set.of(createdUser))
                .build();

        createdGroup = groupRepository.save(createdGroup);

        createdUser.getRoles().addAll(roleService.createRole(RoleEnum.ROLE_GROUP_ADMIN, createdGroup.getId()));

        log.info(createdUser.getRoles().toString());

        return ApiResponse.builder()
                .message("Group is created")
                .success(true)
                .data(Map.of("groupId", createdGroup.getId()))
                .build();
    }

    @Override
    public ApiResponse<Object> deleteGroup(String groupId) {
        try{

            removeGroupPermissions(groupId);
            groupRepository.deleteById(groupId);

        } catch (Exception e){
            throw new InternalException("Invalid group id " + groupId);
        }

        return ApiResponse.builder()
                .message("Group is deleted")
                .success(true)
                .data(Map.of("groupId", groupId))
                .build();
    }


    /***
     * removed the all roles and permissions of all users that are related to current group
     * @param groupId group id
     */
    private void removeGroupPermissions(String groupId) {
        Group group = groupRepository.findById(groupId).orElseThrow(GroupNotFoundException::new);

        roleService.removeRole(group.getCreatedBy(), groupId);
        group.getUsers().forEach(user -> roleService.removeRole(user, groupId));

    }

    @Override
    public ApiResponse<Object> addUserToGroup(String groupId, String userId) {
        Group group = groupRepository.findById(groupId).orElseThrow(GroupNotFoundException::new);
        User user = (User) userService.findUserById(userId).getData();

        // get prev users
        Set<User> users = group.getUsers();
        if(users.contains(user)){
            throw new UserIsAlreadyExistsInGroupException("user is already exists in the group " + group.getName());
        }

        // add group permissions to the user
        user.getRoles().addAll(roleService.createRole(RoleEnum.ROLE_GROUP_USER, groupId));

        // add new user
        users.add(user);

        group.setUsers(users);
        groupRepository.save(group);

        return ApiResponse.builder()
                .message("User is added to the group "+ group.getName())
                .success(true)
                .data(Map.of("groupId", groupId, "userId", userId, "userName", user.getName()))
                .build();
    }


    @Override
    public ApiResponse<Object> removeUserFromGroup(String groupId, String userId) {

        Group group = groupRepository.findById(groupId).orElseThrow(GroupNotFoundException::new);
        User user = (User) userService.findUserById(userId).getData();


        // get prev users
        Set<User> users = group.getUsers();

        if(!users.contains(user)){
            return ApiResponse.builder()
                    .message("Invalid User")
                    .success(true)
                    .data(Map.of("groupId", groupId, "userId", userId, "userName", user.getName()))
                    .build();
        }

        //  remove user
        users.remove(user);

        roleService.removeRole(user, groupId);

        //if removed user and created user is same first remove all users or delete group
        if(user.equals(group.getCreatedBy())){
           throw new CreatorAndDeletedAreSameException();
        }

        group.setUsers(users);


        groupRepository.save(group);


        return ApiResponse.builder()
                .message("User is deleted from group "+ group.getName())
                .success(true)
                .data(Map.of("groupId", groupId, "userId", userId, "userName", user.getName()))
                .build();
    }

    @Override
    public ApiResponse<Object> getGroupMembers(String groupId) {
        Group group = groupRepository.findById(groupId).orElseThrow(GroupNotFoundException::new);

        return ApiResponse.builder()
                .message("List of Group Members of group " + group.getName())
                .success(true)
                .data(customMapper.mapToUserDto(group.getUsers().stream().toList()))
                .build();
    }

    @Override
    public Group findById(String id) {
        return groupRepository.findById(id).orElseThrow(GroupNotFoundException::new);
    }

    @Override
    public ApiResponse<Object> getGroupExpenses(String groupId) {
        List<Expense> expenses = groupRepository.findById(groupId).orElseThrow(GroupNotFoundException::new).getExpenses();



        return ApiResponse.builder()
                .success(true)
                .message("List of expenses")
                .data(customMapper.map(expenses))
                .build();
    }

}
