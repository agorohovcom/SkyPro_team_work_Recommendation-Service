package org.sky_pro.team_work.service;

import lombok.RequiredArgsConstructor;
import org.sky_pro.team_work.Util.RuleChecker;
import org.sky_pro.team_work.domain.Recommendation;
import org.sky_pro.team_work.domain.Rule;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
@RequiredArgsConstructor
public class RecommendationsService {

    private final RuleChecker ruleChecker;
    private final RuleService ruleService;


    public List<Recommendation> getRecommendations(UUID userId) {
        List<Rule> rules = ruleService.getAll();
        List<Recommendation> recommendations = new ArrayList<>();
        for (Rule rule : rules) {
            if (ruleChecker.checkUserByRule(userId, rule))
                recommendations.add(new Recommendation(UUID.randomUUID(), rule.getProductName(), rule.getProductText()));
        }
        return recommendations;
    }

}

