package com.splitwise.exception;

public class InvalidExpenseException extends RuntimeException {
    public InvalidExpenseException(){
        super("Invalid Expense");
    }

    public InvalidExpenseException(String message){
        super(message);
    }
}
