package com.splitwise.factory;

import com.splitwise.enums.ExpenseType;
import com.splitwise.exception.InvalidExpenseTypeException;
import com.splitwise.splitter.ExactAmountSplitter;
import com.splitwise.validator.expense.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ExpenseValidatorFactory {

    @Autowired
    private ExactExpenseValidator exactExpenseValidator;

    @Autowired
    private PercentExpenseValidator percentExpenseValidator;

    @Autowired
    private ShareExpenseValidator shareExpenseValidator;

    @Autowired
    private IndividualExpenseValidator individualExpenseValidator;

    @Autowired
    private EqualExpenseValidator equalExpenseValidator;

    public ExpenseValidator getObject(ExpenseType expenseType){


        switch (expenseType){
            case EXACT -> {
                return exactExpenseValidator;
            }
            case SHARE -> {
                return shareExpenseValidator;
            }
            case PERCENT -> {
                return percentExpenseValidator;
            }
            case EQUAL -> {
                return equalExpenseValidator;
            }
            case INDIVIDUAL -> {
                return individualExpenseValidator;
            }
            default -> throw new InvalidExpenseTypeException(expenseType);
        }
    }



}
