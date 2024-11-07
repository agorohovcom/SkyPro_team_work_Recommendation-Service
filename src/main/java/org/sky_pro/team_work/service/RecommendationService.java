package org.sky_pro.team_work.service;


import org.sky_pro.team_work.model.Recommendation;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class RecommendationService {

    private final JdbcTemplate jdbcTemplate;

    public RecommendationService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Recommendation> getRecommendations(String userId) {
        String sql = """
            SELECT 
                SUM(CASE WHEN p.TYPE = 'DEBIT' and t.TYPE = 'DEPOSIT' THEN AMOUNT ELSE 0 END) as total_debit_deposits,
                SUM(CASE WHEN p.TYPE = 'SAVING' and t.TYPE = 'DEPOSIT' THEN AMOUNT ELSE 0 END) as total_saving_deposits,
                SUM(CASE WHEN p.TYPE = 'DEBIT' and t.TYPE = 'WITHDRAW' THEN AMOUNT ELSE 0 END) as total_debit_withdrawals,
                COUNT(DISTINCT CASE WHEN p.type = 'DEBIT' THEN p.id END) AS debit_product_count,
                COUNT(DISTINCT CASE WHEN p.type = 'INVEST' THEN p.id END) AS invest_product_count,
                COUNT(DISTINCT CASE WHEN p.type = 'CREDIT' THEN p.id END) AS credit_product_count
            FROM PRODUCTS p
            JOIN TRANSACTIONS t ON t.product_id = p.id
            WHERE t.user_id = ?
            """;

        var stats = jdbcTemplate.queryForMap(sql, userId);

        List<Recommendation> recommendations = new ArrayList<>();

        if (meetsInvestCriteria(stats)) {
            recommendations.add(new Recommendation("147f6a0f-3b91-413b-ab99-87f081d60d5a", "Invest",
                    "Откройте свой путь к успеху с индивидуальным инвестиционным счетом (ИИС) от нашего банка!"));
        }

        if (meetsTopSavingCriteria(stats)) {
            recommendations.add(new Recommendation("59efc529-2fff-41af-baff-90ccd7402925", "Top Saving",
                    "Откройте свою собственную «Копилку» с нашим банком!"));
        }

        if (meetsSimpleCreditCriteria(stats)) {
            recommendations.add(new Recommendation("ab138afb-f3ba-4a93-b74f-0fcee86d447f", "Простой кредит",
                    "Откройте мир выгодных кредитов с нами!"));
        }

        return recommendations;
    }

    private boolean meetsInvestCriteria(Map<String, Object> stats) {
        Long totalDebitDeposits = (Long) stats.get("total_debit_deposits");
        Long totalSavingDeposits = (Long) stats.get("total_saving_deposits");
        Long investProductCount = (Long) stats.get("invest_product_count");

        return totalDebitDeposits > 0 && investProductCount == 0 && totalSavingDeposits > 1000;
    }

    private boolean meetsTopSavingCriteria(Map<String, Object> stats) {
        Long totalDebitDeposits = (Long) stats.get("total_debit_deposits");
        Long totalDebitWithdrawals = (Long) stats.get("total_debit_withdrawals");

        return totalDebitDeposits >= 50000 && totalDebitDeposits > totalDebitWithdrawals;
    }

    private boolean meetsSimpleCreditCriteria(Map<String, Object> stats) {
        Long totalDebitWithdrawals = (Long) stats.get("total_debit_withdrawals");
        Long creditProductCount = (Long) stats.get("credit_product_count");

        return creditProductCount == 0 && totalDebitWithdrawals > 100000;
    }
}