package org.sky_pro.team_work.mapper;

import org.sky_pro.team_work.domain.Rule;
import org.sky_pro.team_work.dto.RuleDto;
import org.springframework.stereotype.Service;



@Service
public class Mapper {

    public  Rule RuleDtoToRule(RuleDto dto) {
        Rule rule = new Rule();
        rule.setId(dto.getId());
        rule.setQuery(dto.getQuery());
        rule.setProductText(dto.getProductText());
        rule.setQuery(dto.getQuery());
        rule.setProductId(dto.getProductId());
        return rule;
    }
}
