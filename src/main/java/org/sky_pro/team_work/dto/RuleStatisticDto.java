package org.sky_pro.team_work.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RuleStatisticDto {
    private Long rule_id;
    private int count;
}
