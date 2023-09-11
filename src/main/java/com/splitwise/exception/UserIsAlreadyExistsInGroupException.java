package com.splitwise.exception;

public class UserIsAlreadyExistsInGroupException extends RuntimeException{
    public UserIsAlreadyExistsInGroupException(String message){

        super(message);
    }
    public UserIsAlreadyExistsInGroupException(){
        super("User is Already Exists in the group");
    }
}
