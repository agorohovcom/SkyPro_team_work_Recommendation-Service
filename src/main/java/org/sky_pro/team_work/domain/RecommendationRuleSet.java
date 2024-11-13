package org.sky_pro.team_work.domain;

import java.util.Optional;
import java.util.UUID;

/*
!!!!
ПЕРЕНЕСТИ В domain
*/
public interface RecommendationRuleSet {
    Optional<Recommendation> getRecommendation(UUID userId);
}
