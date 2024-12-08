package org.sky_pro.team_work;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.sky_pro.team_work.repository.RuleStatisticRepository;
import org.sky_pro.team_work.service.RuleStatisticService;


import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class RuleStatisticServiceTest {
    @InjectMocks
    private RuleStatisticService ruleStatisticService;
    @Mock
    private RuleStatisticRepository ruleStatisticRepository;


    @Test
    public void testIncrementCountByRuleId() {
        Long ruleId = 1L;
        ruleStatisticService.incrementCountByRuleId(ruleId);
        verify(ruleStatisticRepository, times(1)).incrementCountByRuleId(ruleId);
    }
}