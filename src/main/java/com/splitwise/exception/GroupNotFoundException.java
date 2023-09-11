package com.splitwise.exception;


public class GroupNotFoundException extends RuntimeException {
    public GroupNotFoundException(String message){
        super(message);
    }
    public GroupNotFoundException(){
        super("Group Not Found");
    }
}

