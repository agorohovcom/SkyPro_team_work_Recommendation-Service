package org.sky_pro.team_work.rules;

import org.sky_pro.team_work.domain.Recommendation;

import java.util.Optional;
import java.util.UUID;

public interface RecommendationRuleSet {
    Optional<Recommendation> getRecommendation(UUID userId);
}
