package org.sky_pro.team_work.domain.products;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.sky_pro.team_work.domain.Recommendation;
import org.sky_pro.team_work.domain.Rule;
import org.sky_pro.team_work.repository.RecommendationsRepository;
import org.sky_pro.team_work.repository.RuleRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@RequiredArgsConstructor
@NoArgsConstructor
@Data
@Component
public class Invest500 extends Recommendation {

    private RecommendationsRepository repository;
    private RuleRepository ruleRepository;

    private List<Rule> rules;

    {
        id = UUID.fromString("147f6a0f-3b91-413b-ab99-87f081d60d5a");
        name = "Invest 500";
        text = """
                Откройте свой путь к успеху с индивидуальным инвестиционным счетом (ИИС) от
                нашего банка! Воспользуйтесь налоговыми льготами и начните инвестировать с умом. Пополните счет до конца года и
                получите выгоду в виде вычета на взнос в следующем налоговом периоде. Не упустите возможность разнообразить свой
                портфель, снизить риски и следить за актуальными рыночными тенденциями. Откройте ИИС сегодня и станьте ближе к
                финансовой независимости!""";

        rules.addAll(ruleRepository.getRulesByProductId(id.toString()));
    }

    @Override
    public Optional<Recommendation> getRecommendation(UUID userId) {
//        return repository.isInvest500(userId) ? Optional.of(new Invest500()) : Optional.empty();
        return checkIsAllRulesOk() ? Optional.of(this) : Optional.empty();
    }

    // А вот тут надо проверить выдают ли все Rules по текущему product_id true
    // ...stream.allMatch() что-то там
    // тут как раз составляются динамические запросы, кешируются
    // пока всё, пойду отдыхать
    // может вынести этот метод в Recommendation
    private boolean checkIsAllRulesOk() {
//        return rules.stream().allMatch()
        return true;
    }
}

