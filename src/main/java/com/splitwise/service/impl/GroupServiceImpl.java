package com.splitwise.service.impl;

import com.splitwise.dto.request.GroupRequestDto;
import com.splitwise.dto.response.ApiResponse;
import com.splitwise.entity.Group;
import com.splitwise.entity.User;
import com.splitwise.exception.CreatorAndDeletedAreSameException;
import com.splitwise.exception.GroupNotFound;
import com.splitwise.exception.UserIsAlreadyExistsInGroup;
import com.splitwise.repository.GroupRepository;
import com.splitwise.service.GroupService;
import com.splitwise.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserService userService;

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

        return ApiResponse.builder()
                .message("Group is created")
                .success(true)
                .data(Map.of("groupId", createdGroup.getId()))
                .build();
    }

    @Override
    public ApiResponse<Object> deleteGroup(UUID groupId) {
        groupRepository.deleteById(groupId);

        return ApiResponse.builder()
                .message("Group is deleted")
                .success(true)
                .data(Map.of("groupId", groupId))
                .build();
    }

    @Override
    public ApiResponse<Object> addUserToGroup(UUID groupId, UUID userId) {
        Group group = groupRepository.findById(groupId).orElseThrow(GroupNotFound::new);
        User user = (User) userService.findUserById(userId).getData();

        // get prev users
        Set<User> users = group.getUsers();
        if(users.contains(user)){
            throw new UserIsAlreadyExistsInGroup("user is already exists in the group " + group.getName());
        }

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
    public ApiResponse<Object> removeUserFromGroup(UUID groupId, UUID userId) {

        Group group = groupRepository.findById(groupId).orElseThrow(GroupNotFound::new);
        User user = (User) userService.findUserById(userId).getData();

        // get prev users
        Set<User> users = group.getUsers();

        // add remove user
        users.remove(user);

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
    public ApiResponse<Object> getGroupMembers(UUID groupId) {
        Group group = groupRepository.findById(groupId).orElseThrow(GroupNotFound::new);

        return ApiResponse.builder()
                .message("List of Group Members of group " + group.getName())
                .success(true)
                .data(group.getUsers())
                .build();
    }
}