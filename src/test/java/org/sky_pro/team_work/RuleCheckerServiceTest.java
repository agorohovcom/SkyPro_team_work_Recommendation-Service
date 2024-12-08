package org.sky_pro.team_work;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.sky_pro.team_work.domain.Query;
import org.sky_pro.team_work.domain.Rule;
import org.sky_pro.team_work.enums.ProductType;
import org.sky_pro.team_work.enums.QueryType;
import org.sky_pro.team_work.repository.RecommendationsRepository;
import org.sky_pro.team_work.service.RuleCheckerService;


import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
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
        query1.setArguments(List.of("DEBIT"));

        Query query2 = new Query();
        query2.setQueryType(QueryType.ACTIVE_USER_OF);
        query2.setArguments(List.of("DEBIT"));

        rule.setQuery(List.of(query1, query2));

        when(repository.checkUserOf(userId, ProductType.DEBIT)).thenReturn(true);
        when(repository.checkActiveUserOf(userId, ProductType.DEBIT)).thenReturn(true);

        boolean result = ruleCheckerService.checkUserByRule(userId, rule);

        assertTrue(result);
    }

    @Test
    public void testCheckUserByRule_OneQueryFalse_ReturnsFalse() {
        UUID userId = UUID.randomUUID();
        Rule rule = new Rule();

        Query query1 = new Query();
        query1.setQueryType(QueryType.USER_OF);
        query1.setArguments(List.of("DEBIT"));

        Query query2 = new Query();
        query2.setQueryType(QueryType.ACTIVE_USER_OF);
        query2.setArguments(List.of("DEBIT"));

        rule.setQuery(List.of(query1, query2));

        when(repository.checkUserOf(userId, ProductType.DEBIT)).thenReturn(true);
        when(repository.checkActiveUserOf(userId, ProductType.DEBIT)).thenReturn(false);

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
        query.setArguments(List.of("DEBIT"));

        rule.setQuery(List.of(query));

        when(repository.checkUserOf(userId, ProductType.DEBIT)).thenReturn(true);

        boolean result = ruleCheckerService.checkUserByRule(userId, rule);

        assertFalse(result);
    }
}