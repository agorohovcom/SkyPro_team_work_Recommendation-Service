package org.sky_pro.team_work.controller;

import lombok.RequiredArgsConstructor;
import org.sky_pro.team_work.domain.Rule;
import org.sky_pro.team_work.dto.RuleDto;
import org.sky_pro.team_work.mapper.Mapper;
import org.sky_pro.team_work.response.RuleResponse;
import org.sky_pro.team_work.dto.RuleStatisticDto;
import org.sky_pro.team_work.response.StatisticResponse;
import org.sky_pro.team_work.service.RuleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rule")
@RequiredArgsConstructor
public class RuleController {
    private final RuleService service;
    private final Mapper mapper;

    @GetMapping
    public RuleResponse getRules() {
        return new RuleResponse(service.getAllRulesDto());
    }

    @PostMapping
    public Rule addRule(@RequestBody RuleDto ruleDto) {
        return service.add(mapper.RuleDtoToRule(ruleDto));
    }

    @DeleteMapping("/{id}")
    public void deleteRule(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/stats")
    public StatisticResponse getRuleStatistics() {
        List<RuleStatisticDto> stats = service.getRuleStatistics();
        return new StatisticResponse(stats);
    }
}
