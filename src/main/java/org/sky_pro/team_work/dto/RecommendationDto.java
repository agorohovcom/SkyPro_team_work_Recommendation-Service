package org.sky_pro.team_work.dto;

import lombok.Data;
import org.sky_pro.team_work.domain.Recommendation;

import java.util.List;
import java.util.UUID;

@Data
public class RecommendationDto {
    private UUID userId;
    private List<Recommendation> recommendations;
}
