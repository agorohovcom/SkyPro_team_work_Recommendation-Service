package org.sky_pro.team_work;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.sky_pro.team_work.domain.Recommendation;
import org.sky_pro.team_work.domain.Rule;
import org.sky_pro.team_work.exception.UserNotFoundException;
import org.sky_pro.team_work.repository.RecommendationsRepository;
import org.sky_pro.team_work.service.RecommendationsService;
import org.sky_pro.team_work.service.RuleCheckerService;
import org.sky_pro.team_work.service.RuleService;
import org.sky_pro.team_work.service.RuleStatisticService;
import java.util.List;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
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
            Rule rule1 = new Rule();
            rule1.setId(1L);
            rule1.setProductId("1c773478-50c5-4fb5-b6b6-1f550fcd1d93");
            rule1.setProductName("Product 1");
            rule1.setProductText("Text 1");

            Rule rule2 = new Rule();
            rule2.setId(2L);
            rule2.setProductId("dd732451-ee07-4b20-bbab-0cd6c6f420a4");
            rule2.setProductName("Product 2");
            rule2.setProductText("Text 2");

            List<Rule> rules = List.of(rule1, rule2);

            when(repository.isUserExistsById(userId)).thenReturn(true);
            when(ruleService.getAll()).thenReturn(rules);
            when(ruleChecker.checkUserByRule(userId, rule1)).thenReturn(true);
            when(ruleChecker.checkUserByRule(userId, rule2)).thenReturn(false);

            List<Recommendation> recommendations = recommendationsService.getRecommendations(userId);

            assertEquals(1, recommendations.size());
            assertEquals(UUID.fromString(rule1.getProductId()), recommendations.getFirst().getId());
            assertEquals(rule1.getProductName(), recommendations.getFirst().getName());
            assertEquals(rule1.getProductText(), recommendations.getFirst().getText());

            verify(ruleStatisticService, times(1)).incrementCountByRuleId(rule1.getId());
            verify(ruleStatisticService, never()).incrementCountByRuleId(rule2.getId());
        }


        @Test
    public void testGetRecommendations_UserDoesNotExist_ThrowsUserNotFoundException() {
        UUID userId = UUID.randomUUID();

        when(repository.isUserExistsById(userId)).thenReturn(false);

        UserNotFoundException exception = assertThrows(UserNotFoundException.class, () -> {
            recommendationsService.getRecommendations(userId);
        });

        assertEquals("Пользователь с id=" + userId + " не найден", exception.getMessage());
    }
}