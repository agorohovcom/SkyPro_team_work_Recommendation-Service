package org.sky_pro.team_work.rules.impl;


import lombok.RequiredArgsConstructor;
import org.sky_pro.team_work.domain.Recommendation;
import org.sky_pro.team_work.domain.RecommendationRuleSet;
import org.sky_pro.team_work.domain.products.Invest500;
import org.sky_pro.team_work.repository.RecommendationsRepository;

import java.util.Optional;
import java.util.UUID;

/*
!!!!
ЭТИ КЛАССЫ НА УДАЛЕНИЕ
 */
//@Component
@RequiredArgsConstructor
public class Invest500RuleSet implements RecommendationRuleSet {

    private final RecommendationsRepository repository;

    @Override
    public Optional<Recommendation> getRecommendation(UUID userId) {
        return repository.isInvest500(userId) ? Optional.of(new Invest500()) : Optional.empty();
    }
}
