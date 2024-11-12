package org.sky_pro.team_work.controller;

import lombok.RequiredArgsConstructor;
import org.sky_pro.team_work.domain.Rule;
import org.sky_pro.team_work.service.RuleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rule")
@RequiredArgsConstructor
public class RuleController {
    private final RuleService ruleService;

    @GetMapping
    public List<Rule> getRules() {
       return ruleService.getAll();
    }

    @PostMapping
    public Rule addRule(@RequestBody Rule rule) {
        return ruleService.add(rule);
    }
}
