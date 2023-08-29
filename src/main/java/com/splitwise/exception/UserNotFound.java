package com.splitwise.exception;

public class UserNotFound extends RuntimeException {
    public UserNotFound(String message){
        super(message);
    }
    public UserNotFound(){
        super("User Not Found");
    }
}
