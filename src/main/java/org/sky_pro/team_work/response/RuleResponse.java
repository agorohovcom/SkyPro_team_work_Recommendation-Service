package org.sky_pro.team_work.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.sky_pro.team_work.dto.RuleDto;

import java.util.List;

@Data
@AllArgsConstructor
public class RuleResponse {
    private List<RuleDto> data;
}

