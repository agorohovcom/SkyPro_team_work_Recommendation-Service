package org.sky_pro.team_work.controller;

import lombok.Getter;
import org.sky_pro.team_work.model.Recommendation;
import org.sky_pro.team_work.service.RecommendationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RecommendationController {

    private final RecommendationService recommendationService;

    public RecommendationController(RecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }

    @GetMapping("/recommendation/{user_id}")
    public ResponseEntity<?> getRecommendations(@PathVariable("user_id") String userId) {
        List<Recommendation> recommendations = recommendationService.getRecommendations(userId);
        return ResponseEntity.ok(new Response(userId, recommendations));
    }

    static class Response {
        private String user_id;
        @Getter
        private List<Recommendation> recommendations;

        public Response(String user_id, List<Recommendation> recommendations) {
            this.user_id = user_id;
            this.recommendations = recommendations;
        }
    }
}