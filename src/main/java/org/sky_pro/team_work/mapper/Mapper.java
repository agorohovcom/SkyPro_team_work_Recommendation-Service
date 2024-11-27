package org.sky_pro.team_work.mapper;

import org.sky_pro.team_work.domain.Rule;
import org.sky_pro.team_work.dto.RuleDto;
import org.springframework.stereotype.Service;

@Service
public class Mapper {

    public Rule ruleDtoToRule(RuleDto dto) {
        Rule rule = new Rule();
        rule.setId(dto.getId());
        rule.setProductName(dto.getProductName());
        rule.setProductId(dto.getProductId());
        rule.setProductText(dto.getProductText());
        rule.setQuery(dto.getQuery());
        return rule;
    }
}
