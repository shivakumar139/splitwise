package com.splitwise.service;

import com.splitwise.dto.request.GroupRequestDto;
import com.splitwise.dto.response.ApiResponse;
import com.splitwise.entity.Group;
import com.splitwise.entity.User;

import java.util.UUID;

public interface GroupService {
    ApiResponse<Object> createGroup(GroupRequestDto groupRequestDto);
    ApiResponse<Object> deleteGroup(String groupId);
    ApiResponse<Object> addUserToGroup(String groupId, String userId);
    ApiResponse<Object> removeUserFromGroup(String groupId, String userId);
    ApiResponse<Object> getGroupMembers(String groupId);

}
