package org.sky_pro.team_work.rules.impl;

import lombok.RequiredArgsConstructor;
import org.sky_pro.team_work.domain.Recommendation;
import org.sky_pro.team_work.domain.UserInfoForRules;
import org.sky_pro.team_work.domain.products.TopSaving;
import org.sky_pro.team_work.repository.RecommendationsRepository;
import org.sky_pro.team_work.rules.RecommendationRuleSet;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class TopSavingRuleSet implements RecommendationRuleSet {

    private final RecommendationsRepository recommendationsRepository;

    @Override
    public Optional<Recommendation> getRecommendation(UUID userId) {
        UserInfoForRules userInfoForRules = recommendationsRepository.findInfoForRules(userId);
        if (userInfoForRules != null && userInfoForRules.isHaveDebit()
                && (userInfoForRules.getDebitDeposit() >= 50000 || userInfoForRules.getSavingDeposit() >= 50000)
                && ((userInfoForRules.getDebitDeposit() - userInfoForRules.getDebitWithdraw()) > 0)) {
            return Optional.of(new TopSaving());
        }
        return Optional.empty();
    }
}

