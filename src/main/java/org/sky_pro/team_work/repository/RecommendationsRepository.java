package org.sky_pro.team_work.repository;

import org.sky_pro.team_work.util.SqlUtil;
import org.sky_pro.team_work.enums.ComparisonType;
import org.sky_pro.team_work.enums.ProductType;
import org.sky_pro.team_work.enums.TransactionType;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class RecommendationsRepository {
    private final JdbcTemplate jdbcTemplate;

    public RecommendationsRepository(@Qualifier("recommendationsJdbcTemplate") JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public boolean isUserExistsById(UUID userId) {
        String sql = SqlUtil.isUserExistsById();
        return Boolean.TRUE.equals(jdbcTemplate.queryForObject(sql, Boolean.class, userId.toString()));
    }

    public boolean checkUserOf(UUID userId, ProductType productType) {
        String sql = SqlUtil.userOf();
        return Boolean.TRUE.equals(jdbcTemplate.queryForObject(sql, Boolean.class, userId, productType.toString()));
    }

    public boolean checkActiveUserOf(UUID userId, ProductType productType) {
        String sql = SqlUtil.activeUserOf();
        return Boolean.TRUE.equals(jdbcTemplate.queryForObject(sql, Boolean.class, productType.toString(), userId));
    }

    public boolean checkTransactionSumCompare(UUID userId, ProductType productType, TransactionType transactionType,
                                              ComparisonType comparison, Integer compareNumber) {
        String sql = SqlUtil.transactionSumCompare(comparison);
        return Boolean.TRUE.equals(jdbcTemplate.queryForObject(sql, Boolean.class,
                productType.toString(), transactionType.toString(), compareNumber, userId));
    }

    public boolean checkTransactionSumCompareDepositWithdraw(UUID userId, ProductType productType,
                                                             ComparisonType comparison) {
        String sql = SqlUtil.transactionSumCompareDepositWithdraw(comparison);
        return Boolean.TRUE.equals(jdbcTemplate.queryForObject(sql, Boolean.class, productType.toString(),
                productType.toString(), userId.toString()));
    }

    public boolean checkProductOrProductSumMoreMoreValue(UUID user, ProductType productType1,
                                                         TransactionType transactionType1,
                                                         ComparisonType comparison1,
                                                         Integer value1, ProductType productType2,
                                                         TransactionType transactionType2,
                                                         ComparisonType comparison2, Integer value2) {
        String sql = SqlUtil.productOrProductTransactionTypeSumMoreMoreValue(comparison1, comparison2);
        return Boolean.TRUE.equals(jdbcTemplate.queryForObject(sql, Boolean.class,
                productType1.toString(), transactionType1.toString(), value1,
                productType2.toString(), transactionType2.toString(), value2,
                user.toString()));
    }
}