package com.splitwise.factory;

import com.splitwise.enums.ExpenseType;
import com.splitwise.exception.InvalidAmountSplitterException;
import com.splitwise.splitter.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AmountSplitterFactory {

    @Autowired
    private ExactAmountSplitter exactAmountSplitter;

    @Autowired
    private EqualAmountSplitter equalAmountSplitter;

    @Autowired
    private PercentAmountSplitter percentAmountSplitter;

    @Autowired
    private ShareAmountSplitter shareAmountSplitter;

    public AmountSplitter getObject(ExpenseType expenseType){
        switch (expenseType){
            case EXACT -> { return exactAmountSplitter;}
            case EQUAL -> {return equalAmountSplitter; }
            case PERCENT -> {return percentAmountSplitter; }
            case SHARE -> { return shareAmountSplitter; }
            default -> throw new InvalidAmountSplitterException();
        }
    }
}
