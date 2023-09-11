package com.splitwise.factory;

import com.splitwise.enums.ExpenseType;
import com.splitwise.exception.InvalidExpenseTypeException;
import com.splitwise.splitter.ExactAmountSplitter;
import com.splitwise.validator.expense.ExactExpenseValidator;
import com.splitwise.validator.expense.ExpenseValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ExpenseValidatorFactory {

    @Autowired
    private ExactExpenseValidator exactExpenseValidator;

    public ExpenseValidator getObject(ExpenseType expenseType){


        switch (expenseType){
            case EXACT -> {
                return exactExpenseValidator;
            }
            default -> throw new InvalidExpenseTypeException(expenseType);
        }
    }



}
