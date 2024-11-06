package org.sky_pro.team_work.service;

import lombok.RequiredArgsConstructor;
import org.sky_pro.team_work.recommendation.RecommendationRuleSet;
import org.sky_pro.team_work.repository.RecommendationsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RecommendationsService {

    private final RecommendationsRepository repository;
    private final List<RecommendationRuleSet> recommendationRuleSet;

    public List<RecommendationRuleSet> getPersonalRecommendations(UUID userId) {

        return recommendationRuleSet
                .stream()
                .filter((e) -> e.checkRecommendation(userId).isPresent())
                .toList();
    }
}
