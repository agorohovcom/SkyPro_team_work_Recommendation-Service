package org.sky_pro.team_work.rules.impl;

import lombok.RequiredArgsConstructor;
import org.sky_pro.team_work.domain.Recommendation;
import org.sky_pro.team_work.domain.products.TopSaving;
import org.sky_pro.team_work.repository.RecommendationsRepository;
import org.sky_pro.team_work.rules.RecommendationRuleSet;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class TopSavingRuleSet implements RecommendationRuleSet {

    private final RecommendationsRepository repository;

    @Override
    public Optional<Recommendation> getRecommendation(UUID userId) {
        return repository.isTopSaving(userId) ? Optional.of(new TopSaving()) : Optional.empty();
    }
}

