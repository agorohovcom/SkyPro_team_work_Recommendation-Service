package org.sky_pro.team_work.service;

import lombok.RequiredArgsConstructor;
import org.sky_pro.team_work.domain.Recommendation;
import org.sky_pro.team_work.domain.Rule;
import org.sky_pro.team_work.exception.UserNotFoundException;
import org.sky_pro.team_work.repository.RecommendationsRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RecommendationsService {

    private final RuleCheckerService ruleChecker;
    private final RuleService ruleService;
    private final RuleStatisticService ruleStatisticService;
    private final RecommendationsRepository repository;

    public List<Recommendation> getRecommendations(UUID userId) {
        isUserExistsById(userId);
        List<Rule> rules = ruleService.getAll();
        List<Recommendation> recommendations = new ArrayList<>();
        for (Rule rule : rules) {
            if (ruleChecker.checkUserByRule(userId, rule)) {
                ruleStatisticService.incrementCountByRuleId(rule.getId());
                recommendations.add(new Recommendation(UUID.randomUUID(), rule.getProductName(), rule.getProductText()));
            }
        }
        return recommendations;
    }

    private void isUserExistsById(UUID userId) {
        if(!repository.isUserExistsById(userId)) {
            throw new UserNotFoundException("Пользователь с id=" + userId + " не найден");
        }
    }
}
//todo изменить в таблице rule тип поля id,чтобы на строке 24 не генерировать рандомный айдишник,а брать из odj rule


