package org.sky_pro.team_work.rules.impl;

import lombok.RequiredArgsConstructor;
import org.sky_pro.team_work.domain.Recommendation;
import org.sky_pro.team_work.domain.UserInfoForRules;
import org.sky_pro.team_work.domain.products.SimpleCredit;
import org.sky_pro.team_work.repository.RecommendationsRepository;
import org.sky_pro.team_work.rules.RecommendationRuleSet;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class SimpleCreditRuleSet implements RecommendationRuleSet {

    private final RecommendationsRepository recommendationsRepository;

    @Override
    public Optional<Recommendation> getRecommendation(UUID userId) {
        UserInfoForRules userInfoForRules = recommendationsRepository.findInfoForRules(userId);
        if (userInfoForRules != null && !userInfoForRules.isHaveCredit()
                && userInfoForRules.getDebitDeposit() - userInfoForRules.getDebitWithdraw() > 0
                && userInfoForRules.getDebitWithdraw() > 10000) {
            return Optional.of(new SimpleCredit());
        }
        return Optional.empty();
    }
}
