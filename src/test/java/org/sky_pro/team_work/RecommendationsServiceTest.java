package org.sky_pro.team_work;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.sky_pro.team_work.domain.Recommendation;
import org.sky_pro.team_work.domain.Rule;
import org.sky_pro.team_work.exception.UserNotFoundException;
import org.sky_pro.team_work.repository.RecommendationsRepository;
import org.sky_pro.team_work.service.RecommendationsService;
import org.sky_pro.team_work.service.RuleCheckerService;
import org.sky_pro.team_work.service.RuleService;
import org.sky_pro.team_work.service.RuleStatisticService;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class RecommendationsServiceTest {

    @InjectMocks
    private RecommendationsService recommendationsService;

    @Mock
    private RuleCheckerService ruleChecker;

    @Mock
    private RuleService ruleService;

    @Mock
    private RuleStatisticService ruleStatisticService;

    @Mock
    private RecommendationsRepository repository;

    @Test
    public void testGetRecommendations_UserExists_ReturnsRecommendations() {
        UUID userId = UUID.randomUUID();
        Rule rule = new Rule();
        List<Rule> rules = new ArrayList<>();
        rules.add(rule);

        Mockito.when(repository.isUserExistsById(userId)).thenReturn(true);
        Mockito.when(ruleService.getAll()).thenReturn(rules);
        Mockito.when(ruleChecker.checkUserByRule(userId, rule)).thenReturn(true);

        List<Recommendation> recommendations = recommendationsService.getRecommendations(userId);

        assertEquals(1, recommendations.size());
        assertEquals(rule.getProductId(), recommendations.get(0).getId());
        assertEquals(rule.getProductName(), recommendations.get(0).getId());
        assertEquals(rule.getProductText(), recommendations.get(0).getId());

        Mockito.verify(ruleStatisticService, Mockito.times(1)).incrementCountByRuleId(rule.getId());
    }

    @Test
    public void testGetRecommendations_UserDoesNotExist_ThrowsUserNotFoundException() {
        UUID userId = UUID.randomUUID();

        Mockito.when(repository.isUserExistsById(userId)).thenReturn(false);

        UserNotFoundException exception = assertThrows(UserNotFoundException.class, () -> {
            recommendationsService.getRecommendations(userId);
        });

        assertEquals("Пользователь с id=" + userId + " не найден", exception.getMessage());
    }
}