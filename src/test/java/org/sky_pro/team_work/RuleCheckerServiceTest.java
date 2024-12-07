package org.sky_pro.team_work;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.sky_pro.team_work.domain.Query;
import org.sky_pro.team_work.domain.Rule;
import org.sky_pro.team_work.enums.ProductType;
import org.sky_pro.team_work.enums.QueryType;
import org.sky_pro.team_work.repository.RecommendationsRepository;
import org.sky_pro.team_work.service.RuleCheckerService;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class RuleCheckerServiceTest {

    @InjectMocks
    private RuleCheckerService ruleCheckerService;

    @Mock
    private RecommendationsRepository repository;

    @Test
    public void testCheckUserByRule_AllQueriesTrue_ReturnsTrue() {
        UUID userId = UUID.randomUUID();
        Rule rule = new Rule();

        Query query1 = new Query();
        query1.setQueryType(QueryType.USER_OF);
        query1.setArguments(Arrays.asList("DEBIT"));

        Query query2 = new Query();
        query2.setQueryType(QueryType.ACTIVE_USER_OF);
        query2.setArguments(Arrays.asList("DEBIT"));

        rule.setQuery(Arrays.asList(query1, query2));

        Mockito.when(repository.checkUserOf(userId, ProductType.DEBIT)).thenReturn(true);
        Mockito.when(repository.checkActiveUserOf(userId, ProductType.DEBIT)).thenReturn(true);

        boolean result = ruleCheckerService.checkUserByRule(userId, rule);

        assertTrue(result);
    }

    @Test
    public void testCheckUserByRule_OneQueryFalse_ReturnsFalse() {
        UUID userId = UUID.randomUUID();
        Rule rule = new Rule();

        Query query1 = new Query();
        query1.setQueryType(QueryType.USER_OF);
        query1.setArguments(Arrays.asList("DEBIT"));

        Query query2 = new Query();
        query2.setQueryType(QueryType.ACTIVE_USER_OF);
        query2.setArguments(Arrays.asList("DEBIT"));

        rule.setQuery(Arrays.asList(query1, query2));

        Mockito.when(repository.checkUserOf(userId, ProductType.DEBIT)).thenReturn(true);
        Mockito.when(repository.checkActiveUserOf(userId, ProductType.DEBIT)).thenReturn(false);

        boolean result = ruleCheckerService.checkUserByRule(userId, rule);

        assertFalse(result);
    }

    @Test
    public void testCheckUserByRule_NegatedQuery_ReturnsCorrectResult() {
        UUID userId = UUID.randomUUID();
        Rule rule = new Rule();

        Query query = new Query();
        query.setQueryType(QueryType.USER_OF);
        query.setNegate(true);
        query.setArguments(Arrays.asList("DEBIT"));

        rule.setQuery(Arrays.asList(query));

        Mockito.when(repository.checkUserOf(userId, ProductType.DEBIT)).thenReturn(true);

        boolean result = ruleCheckerService.checkUserByRule(userId, rule);

         assertFalse(result);
    }
}