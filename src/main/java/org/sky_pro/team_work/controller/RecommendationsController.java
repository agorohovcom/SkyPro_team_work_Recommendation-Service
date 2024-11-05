package org.sky_pro.team_work.controller;

import lombok.RequiredArgsConstructor;
import org.sky_pro.team_work.dto.PersonalRecommendations;
import org.sky_pro.team_work.service.RecommendationsService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class RecommendationsController {
    private final RecommendationsService service;

    @GetMapping(value = "/recommendation/{user_id}")
    public ResponseEntity<PersonalRecommendations> getPersonalRecommendations(
            @PathVariable(name = "user_id") UUID userId) {
        PersonalRecommendations recommendations = new PersonalRecommendations();
        recommendations.setUserId(userId);
        recommendations.setRecommendations(service.getPersonalRecommendations(userId));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(headers)
                .body(recommendations);
    }
}
