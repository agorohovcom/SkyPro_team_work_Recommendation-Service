package org.sky_pro.team_work.dto;

import lombok.Data;
import org.sky_pro.team_work.domain.Recommendation;

import java.util.List;
import java.util.UUID;

@Data
public class RecommendationDto {
    //    @Schema(description = "User ID", format = "uuid")
    private UUID userId;
    //    @Schema(description = "List of recommendations")
    private List<Recommendation> recommendations;
}
