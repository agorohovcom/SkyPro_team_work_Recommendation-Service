package org.sky_pro.team_work.repository;

import org.sky_pro.team_work.Util.SqlUtil;
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

    public boolean checkUserOf(UUID userId, ProductType productType) {
        String sql = SqlUtil.userOf(userId, productType);
        System.out.println("checkUserOf  " + sql);
        return Boolean.TRUE.equals(jdbcTemplate.queryForObject(sql, Boolean.class));
    }

    public boolean checkActiveUserOf(UUID userId, ProductType productType) {
        String sql = SqlUtil.activeUserOf(productType, userId);
        System.out.println("checkActiveUserOf  " + sql);
        return Boolean.TRUE.equals(jdbcTemplate.queryForObject(sql, Boolean.class));
    }

    public boolean checkTransactionSumCompare(UUID userId, ProductType productType, TransactionType transactionType,
                                              ComparisonType comparison, Integer compareNumber) {
        String sql = SqlUtil.transactionSumCompare(productType, transactionType, comparison, compareNumber, userId);
        System.out.println(comparison);
        System.out.println("checkTransactionSumCompare  " + sql);
        return Boolean.TRUE.equals(jdbcTemplate.queryForObject(sql, Boolean.class));
    }

    public boolean checkTransactionSumCompareDepositWithdraw(UUID userId, ProductType productType, ComparisonType comparison) {
        String sql = SqlUtil.transactionSumCompareDepositWithdraw(productType, comparison, userId);
        System.out.println(comparison);
        System.out.println("heckTransactionSumCompareDepositWithdraw  " + sql);
        return Boolean.TRUE.equals(jdbcTemplate.queryForObject(sql, Boolean.class));
    }
}


