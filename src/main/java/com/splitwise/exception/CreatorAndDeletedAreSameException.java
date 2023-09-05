package com.splitwise.exception;

public class CreatorAndDeletedAreSameException extends RuntimeException{
    public CreatorAndDeletedAreSameException(String message){
        super(message);
    }
    public CreatorAndDeletedAreSameException(){
        super("You cannot remove yourself, First remove all members or delete group");
    }
}
