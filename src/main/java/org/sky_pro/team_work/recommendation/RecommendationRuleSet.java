package org.sky_pro.team_work.recommendation;

import java.util.Optional;
import java.util.UUID;

public interface RecommendationRuleSet {
    Optional<RecommendationRuleSet> checkRecommendation(UUID userId);
}
