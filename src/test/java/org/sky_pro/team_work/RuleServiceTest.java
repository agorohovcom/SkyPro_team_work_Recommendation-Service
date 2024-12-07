package org.sky_pro.team_work;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.sky_pro.team_work.domain.Query;
import org.sky_pro.team_work.domain.Rule;
import org.sky_pro.team_work.domain.RuleStatistic;
import org.sky_pro.team_work.dto.RuleStatisticDto;
import org.sky_pro.team_work.exception.RuleNotFoundException;
import org.sky_pro.team_work.repository.RuleRepository;
import org.sky_pro.team_work.service.RuleService;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class RuleServiceTest {

    @InjectMocks
    private RuleService ruleService;

    @Mock
    private RuleRepository repository;

    @Test
    public void testGetAll_ReturnsListOfRules() {
        Rule rule1 = new Rule();
        rule1.setProductName("Product 1");
        rule1.setProductId("product-1-id");
        rule1.setProductText("Description 1");
        rule1.setQuery(Arrays.asList(new Query()));

        Rule rule2 = new Rule();
        rule2.setProductName("Product 2");
        rule2.setProductId("product-2-id");
        rule2.setProductText("Description 2");
        rule2.setQuery(Arrays.asList(new Query()));

        Mockito.when(repository.findAllWithQueries()).thenReturn(Arrays.asList(rule1, rule2));

        List<Rule> result = ruleService.getAll();

        assertEquals(2, result.size());
        assertEquals("Product 1", result.get(0).getProductName());
        assertEquals("Product 2", result.get(1).getProductName());
    }

    @Test
    public void testAdd_SavesRule() {
        Rule rule = new Rule();
        rule.setProductName("New Product");

        Mockito.when(repository.save(rule)).thenReturn(rule);

        Rule result = ruleService.add(rule);

        assertEquals(rule, result);
        Mockito.verify(repository, Mockito.times(1)).save(rule);
    }

    @Test
    public void testDelete_ValidId_DeletesRule() {
        Long id = 1L;

        Mockito.when(repository.existsById(id)).thenReturn(true);

        ruleService.delete(id);

        Mockito.verify(repository, Mockito.times(1)).deleteById(id);
    }

    @Test
    public void testDelete_InvalidId_ThrowsRuleNotFoundException() {
        Long id = 1L;

        Mockito.when(repository.existsById(id)).thenReturn(false);

        RuleNotFoundException exception = assertThrows(RuleNotFoundException.class, () -> {
            ruleService.delete(id);
        });

        assertEquals("Правило с id=1 не найдено", exception.getMessage());
    }

    @Test
    public void testGetRuleStatistics_ReturnsListOfStatistics() {
        Rule rule = new Rule();
        rule.setId(1L);

        RuleStatistic statistic = new RuleStatistic();
        statistic.setCount(5);

        rule.setRuleStatistic(statistic);

        Mockito.when(repository.findAll()).thenReturn(Arrays.asList(rule));

        List<RuleStatisticDto> result = ruleService.getRuleStatistics();

        assertEquals(1, result.size());
        assertEquals(Long.valueOf(1), result.get(0).getRule_id());
        assertEquals(5, result.get(0).getCount());
    }
}