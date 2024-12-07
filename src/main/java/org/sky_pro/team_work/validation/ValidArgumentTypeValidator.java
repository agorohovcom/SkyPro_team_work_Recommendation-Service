package org.sky_pro.team_work.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.sky_pro.team_work.enums.ComparisonType;
import org.sky_pro.team_work.enums.ProductType;
import org.sky_pro.team_work.enums.TransactionType;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ValidArgumentTypeValidator
        implements ConstraintValidator<ValidArgumentType, String> {

    private final List<String> validArguments;

    public ValidArgumentTypeValidator() {
        this.validArguments = Stream.of(
                Arrays.stream(ComparisonType.values()).map(ComparisonType::getOperator),
                Arrays.stream(ProductType.values()).map(Enum::name),
                Arrays.stream(TransactionType.values()).map(Enum::name)
        ).flatMap(s -> s).collect(Collectors.toList());
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (isNumeric(value)) {
            return true;
        }
        return value == null || validArguments.contains(value);
    }

    private boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
