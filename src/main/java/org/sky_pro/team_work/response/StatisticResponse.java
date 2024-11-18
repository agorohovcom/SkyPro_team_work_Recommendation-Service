package org.sky_pro.team_work.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.sky_pro.team_work.dto.RuleStatisticDto;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class StatisticResponse {
    private List<RuleStatisticDto> stats;
}
