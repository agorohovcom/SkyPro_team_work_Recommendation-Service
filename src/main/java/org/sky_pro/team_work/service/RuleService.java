package org.sky_pro.team_work.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.sky_pro.team_work.domain.Rule;
import org.sky_pro.team_work.repository.RuleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RuleService {
    private final RuleRepository repository;

    public List<Rule> getAll() {
        return repository.findAll();
    }

    @Transactional
    public Rule add(Rule rule) {
        return repository.save(rule);
    }
}
