package com.splitwise.exception;

public class UserIsAlreadyExistsInGroup extends RuntimeException{
    public UserIsAlreadyExistsInGroup(String message){

        super(message);
    }
    public UserIsAlreadyExistsInGroup(){
        super("User is Already Exists in the group");
    }
}
