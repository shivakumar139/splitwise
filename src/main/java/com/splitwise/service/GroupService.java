package com.splitwise.service;

import com.splitwise.dto.request.GroupRequestDto;
import com.splitwise.dto.response.ApiResponse;
import com.splitwise.entity.Group;
import com.splitwise.entity.User;

import java.util.UUID;

public interface GroupService {
    ApiResponse<Object> createGroup(GroupRequestDto groupRequestDto);
    ApiResponse<Object> deleteGroup(UUID groupId);
    ApiResponse<Object> addUserToGroup(UUID groupId, UUID userId);
    ApiResponse<Object> removeUserFromGroup(UUID groupId, UUID userId);
    ApiResponse<Object> getGroupMembers(UUID groupId);

}
