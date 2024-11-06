package org.sky_pro.team_work.repository;

import org.sky_pro.team_work.domain.UserInfoForRules;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.UUID;

import static org.sky_pro.team_work.Util.SqlUtil.findUserInfoSQL;

@Repository
public class RecommendationsRepository {
    private final JdbcTemplate jdbcTemplate;

    public RecommendationsRepository(@Qualifier("recommendationsJdbcTemplate") JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public UserInfoForRules findInfoForRules(UUID user) {
        return jdbcTemplate.query(findUserInfoSQL, new Object[]{user, user, user, user}, rs -> {
            if (rs.next()) {
                UserInfoForRules userInfo = new UserInfoForRules();
                userInfo.setHaveCredit(rs.getBoolean("isHaveCredit"));
                userInfo.setHaveDebit(rs.getBoolean("isHaveDebit"));
                userInfo.setHaveInvest(rs.getBoolean("isHaveInvest"));
                userInfo.setDebitDeposit(rs.getInt("debitDeposit"));
                userInfo.setDebitWithdraw(rs.getInt("debitWithdraw"));
                userInfo.setSavingDeposit(rs.getInt("savingDeposit"));
                return userInfo;
            }
            return null;
        });
    }
}
