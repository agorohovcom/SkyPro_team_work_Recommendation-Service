package org.sky_pro.team_work.service;


import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.sky_pro.team_work.repository.RuleRepository;
import org.sky_pro.team_work.repository.RuleStatisticRepository;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
@Data
public class RuleStatisticService {
    private final RuleStatisticRepository ruleStatisticRepository;
    private final RuleRepository ruleRepository;

    public void incrementCountByRuleId(Long ruleId) {
        ruleStatisticRepository.incrementCountByRuleId(ruleId);
    }
}
