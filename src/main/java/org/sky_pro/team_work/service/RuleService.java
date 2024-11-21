package org.sky_pro.team_work.service;

import lombok.RequiredArgsConstructor;
import org.sky_pro.team_work.domain.Rule;
import org.sky_pro.team_work.domain.RuleStatistic;
import org.sky_pro.team_work.dto.RuleStatisticDto;
import org.sky_pro.team_work.repository.RuleRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RuleService {

    private final RuleRepository repository;

    @Cacheable("rules")
    public List<Rule> getAll() {
//        return repository.findAll();
        return repository.findAllWithQueries();
    }

    @CacheEvict(value = "rules", allEntries = true)
    public Rule add(Rule rule) {
        return repository.save(rule);
    }

    @CacheEvict(value = "rules", allEntries = true)
    public void delete(Long id) {
        repository.deleteById(id);
    }

    public List<RuleStatisticDto> getRuleStatistics() {
        return repository.findAll().stream()
                .map(rule -> {
                    RuleStatistic ruleStatistic = rule.getRuleStatistic() != null
                            ? rule.getRuleStatistic()
                            : new RuleStatistic(rule.getId(), 0);
                    return new RuleStatisticDto(rule.getId(), ruleStatistic.getCount());
                })
                .toList();
    }
}

