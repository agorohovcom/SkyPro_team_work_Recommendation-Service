package org.sky_pro.team_work.enums;

import lombok.Getter;


@Getter
public enum ComparisonType {

    GREATER_THAN(">"),
    LESS_THAN("<"),
    EQUALS("="),
    GREATER_THAN_OR_EQUAL(">="),
    LESS_THAN_OR_EQUAL("<=");

    private final String operator;

    ComparisonType(String operator) {
        this.operator = operator;
    }

}

