package org.sky_pro.team_work.mapper;

import org.sky_pro.team_work.domain.Rule;
import org.sky_pro.team_work.dto.RuleDto;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

    public Rule ruleDtoToRule(RuleDto dto) {
        Rule rule = new Rule();
        rule.setProductName(dto.getProductName());
        rule.setProductText(dto.getProductText());
        rule.setQuery(dto.getQuery());
        rule.setProductId(dto.getProductId());
        return rule;
    }

//    public RuleDto ruleToRuleDto(Rule rule) {
//        RuleDto ruleDto = new RuleDto();
//        ruleDto.setProductName(rule.getProductName());
//        ruleDto.setProductText(rule.getProductText());
//        ruleDto.setQuery(rule.getQuery());
//        ruleDto.setProductId(rule.getProductId());
//        return ruleDto;
//    }
}
