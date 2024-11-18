package org.sky_pro.team_work.repository;

import jakarta.transaction.Transactional;
import org.sky_pro.team_work.domain.RuleStatistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RuleStatisticRepository extends JpaRepository<RuleStatistic, Long> {

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO rule_statistic (rule_id, count) VALUES (:ruleId, 1) " +
            "ON CONFLICT (rule_id) DO UPDATE SET count = rule_statistic.count + 1", nativeQuery = true)
    void incrementCountByRuleId(@Param("ruleId") Long ruleId);
}


