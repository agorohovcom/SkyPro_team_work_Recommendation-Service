package org.sky_pro.team_work.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.sky_pro.team_work.dto.RuleDto;
import org.sky_pro.team_work.dto.RuleStatisticDto;
import org.sky_pro.team_work.mapper.Mapper;
import org.sky_pro.team_work.response.RuleResponse;
import org.sky_pro.team_work.response.StatisticResponse;
import org.sky_pro.team_work.service.RuleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rule")
@RequiredArgsConstructor
@Validated
public class RuleController {
    private final RuleService service;
    private final Mapper mapper;

    @GetMapping
    public ResponseEntity<RuleResponse> getRules() {
        RuleResponse result = new RuleResponse(service.getAllRulesDto());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(result);
    }

    @PostMapping
    public ResponseEntity<RuleDto> addRule(@Valid @RequestBody RuleDto ruleDto) {
        service.add(mapper.ruleDtoToRule(ruleDto));
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ruleDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRule(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/stats")
    public ResponseEntity<StatisticResponse> getRuleStatistics() {
        List<RuleStatisticDto> stats = service.getRuleStatistics();
        StatisticResponse result = new StatisticResponse(stats);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(result);
    }
}
