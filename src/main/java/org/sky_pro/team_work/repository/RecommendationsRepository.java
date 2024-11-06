package org.sky_pro.team_work.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@Slf4j
public class RecommendationsRepository {
    private final JdbcTemplate jdbcTemplate;

    public RecommendationsRepository(
            @Qualifier("recommendationsJdbcTemplate") JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public boolean isUserUsedTransactionType(UUID userId, String transactionType) {
        try {
            return Boolean.TRUE.equals(jdbcTemplate.queryForObject(
                    "SELECT EXISTS (SELECT * FROM TRANSACTIONS t " +
                            "LEFT JOIN PRODUCTS p ON t.PRODUCT_ID = p.ID " +
                            "WHERE t.USER_ID = ? AND p.TYPE = ?)",
                    Boolean.class,
                    userId,
                    transactionType
            ));
        } catch (DataAccessException e) {
            log.error("DataAccessException in request with userId={} and transactionType={}",
                    userId,
                    transactionType);
            return false;
        }
    }

    public Long sumAmountOfTransactionsByProduct(UUID userId, String productType, String transactionType) {
        Long result = null;
        try {
            result = jdbcTemplate.queryForObject(
                    "SELECT SUM(t.AMOUNT) FROM TRANSACTIONS t " +
                            "LEFT JOIN PRODUCTS p ON t.PRODUCT_ID = p.ID " +
                            "WHERE t.USER_ID = ? " +
                            "AND p.TYPE = ? " +
                            "AND t.TYPE = ?",
                    Long.class,
                    userId,
                    productType,
                    transactionType);
        } catch (DataAccessException e) {
            log.error("DataAccessException in request with userId={} and productType={} and transactionType={}",
                    userId,
                    productType,
                    transactionType);
        }
        return result != null ? result : 0L;
    }
}
