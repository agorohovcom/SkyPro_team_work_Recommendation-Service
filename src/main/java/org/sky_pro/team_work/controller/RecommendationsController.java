package org.sky_pro.team_work.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Get personal recommendations for user by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the recommendations",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = RecommendationDto.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content(mediaType = "application/json"))})
    @GetMapping("/{user_Id}")
    public ResponseEntity<RecommendationDto> getPersonalRecommendations(
            @Parameter(description = "User id to get personal recommendations")
            @PathVariable UUID user_Id) {

        RecommendationDto recommendations = new RecommendationDto();
        recommendations.setUserId(user_Id);
        recommendations.setRecommendations(service.getRecommendations(user_Id));

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(recommendations);
    }
}
