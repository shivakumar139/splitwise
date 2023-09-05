package com.splitwise.exception;


public class GroupNotFound extends RuntimeException {
    public GroupNotFound(String message){
        super(message);
    }
    public GroupNotFound(){
        super("Group Not Found");
    }
}

