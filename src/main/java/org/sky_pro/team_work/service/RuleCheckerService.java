package org.sky_pro.team_work.service;


import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.sky_pro.team_work.domain.Query;
import org.sky_pro.team_work.domain.Rule;
import org.sky_pro.team_work.enums.ComparisonType;
import org.sky_pro.team_work.enums.ProductType;
import org.sky_pro.team_work.enums.TransactionType;
import org.sky_pro.team_work.repository.RecommendationsRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Data
@RequiredArgsConstructor
@Component
public class RuleCheckerService {
    private final RecommendationsRepository repository;

    public boolean checkUserByRule(UUID userId, Rule rule) {
        for (Query query : rule.getQuery()) {
            System.out.println(query.getQueryType());
            boolean result = switch (query.getQueryType()) {
                case USER_OF -> repository.checkUserOf(userId, ProductType.valueOf(query.getArguments().get(0)));
                case ACTIVE_USER_OF ->
                        repository.checkActiveUserOf(userId, ProductType.valueOf(query.getArguments().get(0)));

                case TRANSACTION_SUM_COMPARE -> repository.checkTransactionSumCompare(userId,
                        ProductType.valueOf(query.getArguments().get(0)),
                        TransactionType.valueOf(query.getArguments().get(1)),
                        ComparisonType.fromOperator(query.getArguments().get(2)),
                        Integer.parseInt(query.getArguments().get(3)));

                case TRANSACTION_SUM_COMPARE_DEPOSIT_WITHDRAW ->
                        repository.checkTransactionSumCompareDepositWithdraw(userId,
                                ProductType.valueOf(query.getArguments().get(0)),
                                ComparisonType.fromOperator(query.getArguments().get(1)));

                case PRODUCT_OR_PRODUCT_SUMS_DEPOSIT_MORE_VALUE -> repository.checkProductOrProductSumMoreMoreValue(userId,
                        ProductType.valueOf(query.getArguments().get(0)),
                        TransactionType.valueOf(query.getArguments().get(1)),
                        ComparisonType.fromOperator(query.getArguments().get(2)),
                        Integer.parseInt(query.getArguments().get(3)),
                        ProductType.valueOf(query.getArguments().get(4)),
                        TransactionType.valueOf(query.getArguments().get(5)),
                        ComparisonType.fromOperator(query.getArguments().get(6)),
                        Integer.parseInt(query.getArguments().get(7)));
            };

            if (query.getNegate() != null && query.getNegate()) {
                result = !result;
            }

            if (!result) {
                return false;
            }
        }
        return true;
    }

}

