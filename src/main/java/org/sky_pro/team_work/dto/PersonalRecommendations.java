package org.sky_pro.team_work.dto;

import lombok.Data;
import org.sky_pro.team_work.recommendation.RecommendationRuleSet;

import java.util.List;
import java.util.UUID;

@Data
public class PersonalRecommendations {
    private UUID userId;
    private List<RecommendationRuleSet> recommendations;
}
