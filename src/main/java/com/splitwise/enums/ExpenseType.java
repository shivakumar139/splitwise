package com.splitwise.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ExpenseType {
    EXACT("EXACT"),
    EQUAL("EQUAL"),
    PERCENT("PERCENT"),
    SHARE("SHARE"),
    INDIVIDUAL("INDIVIDUAL");

    private final String value;

    ExpenseType(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static ExpenseType fromValue(String value) {
        for (ExpenseType type : ExpenseType.values()) {
            if (type.getValue().equalsIgnoreCase(value)) {
                return type;
            }
        }
        return null; // Handle unknown values as needed
    }
}
