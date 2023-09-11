package com.splitwise.factory;

import com.splitwise.enums.ExpenseType;
import com.splitwise.exception.InvalidAmountSplitterException;
import com.splitwise.splitter.AmountSplitter;
import com.splitwise.splitter.ExactAmountSplitter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AmountSplitterFactory {

    @Autowired
    private ExactAmountSplitter exactAmountSplitter;

    public AmountSplitter getObject(ExpenseType expenseType){
        switch (expenseType){
            case EXACT -> { return exactAmountSplitter;}
            default -> throw new InvalidAmountSplitterException();
        }
    }
}
