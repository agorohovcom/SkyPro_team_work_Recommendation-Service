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
    private final RuleService service;


    @GetMapping
    public List<Rule> getRules() {
        return service.getAll();
    }

    @PostMapping
    public Rule addRule(@RequestBody Rule rule) {
        return service.add(rule);
    }

}
