package org.sky_pro.team_work.enums;

import lombok.Getter;

@Getter
public enum ComparisonType {

    GREATER_THAN(">"),
    LESS_THAN("<"),
    EQUALS("="),
    GREATER_THAN_OR_EQUAL(">="),
    LESS_THAN_OR_EQUAL("<="),
    OR("||");

    private final String operator;

    public static ComparisonType fromOperator(String operator) {
        for (ComparisonType type : ComparisonType.values()) {
            if (type.getOperator().equals(operator)) {
                return type;
            }
        }
        throw new IllegalArgumentException("No enum constant for operator: " + operator);
    }

    ComparisonType(String operator) {
        this.operator = operator;
    }

}

