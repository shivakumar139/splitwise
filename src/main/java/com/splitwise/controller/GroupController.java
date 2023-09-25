package com.splitwise.controller;

import com.splitwise.dto.request.GroupRequestDto;
import com.splitwise.dto.response.ApiResponse;
import com.splitwise.service.GroupService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/groups")
public class GroupController {
    @Autowired
    private GroupService groupService;


    @PreAuthorize("principal.id == #groupRequestDto.createdBy")
    @PostMapping
    public ResponseEntity<ApiResponse<Object>> createGroup(@Valid @RequestBody GroupRequestDto groupRequestDto){
        return new ResponseEntity<>(groupService.createGroup(groupRequestDto), HttpStatus.CREATED);
    }


    @PreAuthorize("hasRole('ROLE_GROUP_ADMIN_' + #groupId)")
    @GetMapping("/{groupId}/{userId}")
    public ResponseEntity<ApiResponse<Object>> addUserToGroup(@PathVariable("groupId") String groupId, @PathVariable("userId") String userId){
        return new ResponseEntity<>(groupService.addUserToGroup(groupId, userId), HttpStatus.OK);

    }


    @PreAuthorize("hasRole('ROLE_GROUP_ADMIN_' + #groupId)")
    @DeleteMapping("/{groupId}")
    public ResponseEntity<ApiResponse<Object>> deleteGroup(@PathVariable String groupId){
        return new ResponseEntity<>(groupService.deleteGroup(groupId), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('GROUP_READ_USERS_' + #groupId)")
    @GetMapping("/{groupId}")
    public ResponseEntity<ApiResponse<Object>> getGroupMembers(@PathVariable String groupId){
        return new ResponseEntity<>(groupService.getGroupMembers(groupId), HttpStatus.OK);

    }


    @PreAuthorize("hasRole('ROLE_GROUP_ADMIN_' + #groupId)")
    @DeleteMapping("/{groupId}/{userId}")
    public ResponseEntity<ApiResponse<Object>> removeUserFromGroup(@PathVariable("groupId") String groupId, @PathVariable("userId") String userId){
        return new ResponseEntity<>(groupService.removeUserFromGroup(groupId, userId), HttpStatus.OK);

    }

    // get group expenses by id
    @PreAuthorize("hasAuthority('GROUP_READ_EXPENSE_' + #groupId)")
    @GetMapping("/expenses/{groupId}")
    public ResponseEntity<ApiResponse<Object>> getGroupExpenses(@PathVariable String groupId){
        return new ResponseEntity<>(groupService.getGroupExpenses(groupId), HttpStatus.OK);

    }
}
