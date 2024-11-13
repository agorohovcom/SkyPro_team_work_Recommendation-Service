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
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@RequiredArgsConstructor
@NoArgsConstructor
@Data
@Component
public class SimpleCredit extends Recommendation {

    private RecommendationsRepository repository;
    private RuleRepository ruleRepository;

    private List<Rule> rules;


    {
        id = UUID.fromString("ab138afb-f3ba-4a93-b74f-0fcee86d447f");
        name = "Простой кредит";
        text = """
                Откройте мир выгодных кредитов с нами!
                
                Ищете способ быстро и без лишних хлопот получить нужную сумму? Тогда наш выгодный кредит — именно то, что вам нужно! Мы предлагаем низкие процентные ставки, гибкие условия и индивидуальный подход к каждому клиенту.
                
                Почему выбирают нас:
                
                Быстрое рассмотрение заявки. Мы ценим ваше время, поэтому процесс рассмотрения заявки занимает всего несколько часов.
                
                Удобное оформление. Подать заявку на кредит можно онлайн на нашем сайте или в мобильном приложении.
                
                Широкий выбор кредитных продуктов. Мы предлагаем кредиты на различные цели: покупку недвижимости, автомобиля, образование, лечение и многое другое.
                
                Не упустите возможность воспользоваться выгодными условиями кредитования от нашей компании!""";

        rules.addAll(ruleRepository.getRulesByProductId(id.toString()));
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
