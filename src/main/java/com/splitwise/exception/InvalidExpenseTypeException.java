package com.splitwise.exception;

import com.splitwise.enums.ExpenseType;

public class InvalidExpenseTypeException extends RuntimeException{
    public InvalidExpenseTypeException(){
        super("Invalid Expense type");
    }
    public InvalidExpenseTypeException(ExpenseType expenseType){
        super("Invalid Expense type " + expenseType);
    }
}
