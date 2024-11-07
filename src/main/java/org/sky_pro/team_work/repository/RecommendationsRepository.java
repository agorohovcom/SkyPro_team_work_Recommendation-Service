package org.sky_pro.team_work.repository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.UUID;

import static org.sky_pro.team_work.Util.SqlUtil.*;

@Repository
public class RecommendationsRepository {
    private final JdbcTemplate jdbcTemplate;

    public RecommendationsRepository(@Qualifier("recommendationsJdbcTemplate") JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public boolean isInvest500(UUID user) {
        return Boolean.TRUE.equals(jdbcTemplate.queryForObject(checkInvest500, Boolean.class, user, user, user));
    }

    public boolean isTopSaving(UUID user) {
        return Boolean.TRUE.equals(jdbcTemplate.queryForObject(checkTopSaving, Boolean.class,user, user));
    }

    public boolean isSimpleCredit(UUID user) {
        return Boolean.TRUE.equals(jdbcTemplate.queryForObject(checkSimpleCredit, Boolean.class, user, user));
    }
}
