package org.sky_pro.team_work.service;

import lombok.RequiredArgsConstructor;
import org.sky_pro.team_work.domain.Query;
import org.sky_pro.team_work.domain.Rule;
import org.sky_pro.team_work.domain.RuleStatistic;
import org.sky_pro.team_work.dto.RuleDto;
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
        return repository.findAllWithQueries();
    }

    @Cacheable("rules_dto")
    public List<RuleDto> getAllRulesDto() {
        List<Rule> rules = getAll();
        return rules.stream()
                .map(rule -> new RuleDto(
                        rule.getId(),
                        rule.getProductName(),
                        rule.getProductId(),
                        rule.getProductText(),
                        rule.getQuery().stream()
                                .map(query -> new Query(
                                        query.getQueryType(),
                                        query.getArguments(),
                                        query.getNegate()
                                ))
                                .toList()))
                .toList();
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

