package com.splitwise.controller;

import com.splitwise.dto.request.GroupRequestDto;
import com.splitwise.dto.response.ApiResponse;
import com.splitwise.entity.Group;
import com.splitwise.service.GroupService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/groups")
public class GroupController {
    @Autowired
    private GroupService groupService;

    @PostMapping
    public ResponseEntity<ApiResponse<Object>> createGroup(@Valid @RequestBody GroupRequestDto groupRequestDto){
        return new ResponseEntity<>(groupService.createGroup(groupRequestDto), HttpStatus.CREATED);
    }

    @GetMapping("/{groupId}/{userId}")
    public ResponseEntity<ApiResponse<Object>> addUserToGroup(@PathVariable("groupId") UUID groupId, @PathVariable("userId") UUID userId){
        return new ResponseEntity<>(groupService.addUserToGroup(groupId, userId), HttpStatus.OK);

    }

    @DeleteMapping("/{groupId}")
    public ResponseEntity<ApiResponse<Object>> deleteGroup(@PathVariable UUID groupId){
        return new ResponseEntity<>(groupService.deleteGroup(groupId), HttpStatus.OK);
    }

    @GetMapping("/{groupId}")
    public ResponseEntity<ApiResponse<Object>> getGroupMembers(@PathVariable UUID groupId){
        return new ResponseEntity<>(groupService.getGroupMembers(groupId), HttpStatus.OK);

    }


    @DeleteMapping("/{groupId}/{userId}")
    public ResponseEntity<ApiResponse<Object>> removeUserFromGroup(@PathVariable("groupId") UUID groupId, @PathVariable("userId") UUID userId){
        return new ResponseEntity<>(groupService.removeUserFromGroup(groupId, userId), HttpStatus.OK);

    }
}
