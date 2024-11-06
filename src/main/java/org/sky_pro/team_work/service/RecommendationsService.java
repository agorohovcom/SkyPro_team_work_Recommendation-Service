package org.sky_pro.team_work.service;

import lombok.RequiredArgsConstructor;

import org.sky_pro.team_work.domain.Recommendation;
import org.sky_pro.team_work.rules.RecommendationRuleSet;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecommendationsService {

    private final List<RecommendationRuleSet> ruleSets;

    public List<Recommendation> getRecommendations(UUID userId) {
        return ruleSets.stream()
                .map(ruleSet -> ruleSet.getRecommendation(userId))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }
}
