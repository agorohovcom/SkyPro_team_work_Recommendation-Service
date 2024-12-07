package org.sky_pro.team_work;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.sky_pro.team_work.repository.RuleRepository;
import org.sky_pro.team_work.repository.RuleStatisticRepository;
import org.sky_pro.team_work.service.RuleStatisticService;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class RuleStatisticServiceTest {

    @InjectMocks
    private RuleStatisticService ruleStatisticService;

    @Mock
    private RuleStatisticRepository ruleStatisticRepository;

    @Mock
    private RuleRepository ruleRepository;

    @Test
    public void testIncrementCountByRuleId() {
        Long ruleId = 1L;

        ruleStatisticService.incrementCountByRuleId(ruleId);

        verify(ruleStatisticRepository, Mockito.times(1)).incrementCountByRuleId(ruleId);
    }
}