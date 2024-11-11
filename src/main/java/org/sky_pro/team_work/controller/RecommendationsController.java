package org.sky_pro.team_work.controller;

import lombok.RequiredArgsConstructor;
import org.sky_pro.team_work.dto.RecommendationDto;
import org.sky_pro.team_work.service.RecommendationsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/recommendation")
@RequiredArgsConstructor
public class RecommendationsController {

    private final RecommendationsService service;

    @GetMapping("/{user_Id}")
    public ResponseEntity<RecommendationDto> getPersonalRecommendations(@PathVariable UUID user_Id) {

        RecommendationDto recommendations = new RecommendationDto();
        recommendations.setUserId(user_Id);
        recommendations.setRecommendations(service.getRecommendations(user_Id));

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(recommendations);
    }
}
