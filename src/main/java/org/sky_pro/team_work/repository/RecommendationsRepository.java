package org.sky_pro.team_work.repository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class RecommendationsRepository {
    private final JdbcTemplate jdbcTemplate;

    public RecommendationsRepository(
            @Qualifier("recommendationsJdbcTemplate") JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public boolean isUserUsedTransactionType(UUID userId, String type) {
        try {
            return Boolean.TRUE.equals(jdbcTemplate.queryForObject(
                    "SELECT EXISTS (SELECT * FROM TRANSACTIONS t " +
                            "LEFT JOIN PRODUCTS p ON t.PRODUCT_ID = p.ID " +
                            "WHERE t.USER_ID = ? AND p.TYPE = ?)",
                    Boolean.class,
                    userId,
                    type
            ));
        } catch (DataAccessException e) {
            return false;
        }
    }
}
