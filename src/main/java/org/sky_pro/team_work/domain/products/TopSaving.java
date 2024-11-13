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
public class TopSaving extends Recommendation {

    private RecommendationsRepository repository;
    private RuleRepository ruleRepository;

    private List<Rule> rules;

    {
        id = UUID.fromString("59efc529-2fff-41af-baff-90ccd7402925");
        name = "Top Saving";
        text = """
                Откройте свою собственную «Копилку» с нашим банком! «Копилка» — это уникальный банковский инструмент, который поможет вам легко и удобно накапливать деньги на важные цели. Больше никаких забытых чеков и потерянных квитанций — всё под контролем!
                
                Преимущества «Копилки»:
                
                Накопление средств на конкретные цели. Установите лимит и срок накопления, и банк будет автоматически переводить определенную сумму на ваш счет.
                
                Прозрачность и контроль. Отслеживайте свои доходы и расходы, контролируйте процесс накопления и корректируйте стратегию при необходимости.
                
                Безопасность и надежность. Ваши средства находятся под защитой банка, а доступ к ним возможен только через мобильное приложение или интернет-банкинг.
                
                Начните использовать «Копилку» уже сегодня и станьте ближе к своим финансовым целям!""";

        rules.addAll(ruleRepository.getRulesByProductId(id.toString()));
    }

    @Override
    public Optional<Recommendation> getRecommendation(UUID userId) {
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

