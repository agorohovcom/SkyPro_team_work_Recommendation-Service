package org.sky_pro.team_work.recommendation;

import lombok.RequiredArgsConstructor;
import org.sky_pro.team_work.repository.RecommendationsRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class Invest500Recommendation extends Recommendation {

    private final RecommendationsRepository repository;

    {
        this.id = UUID.fromString("147f6a0f-3b91-413b-ab99-87f081d60d5a");
        this.name = "Invest 500";
        this.text = "Откройте свой путь к успеху с индивидуальным инвестиционным счетом (ИИС) от нашего банка! " +
                "Воспользуйтесь налоговыми льготами и начните инвестировать с умом. " +
                "Пополните счет до конца года и получите выгоду в виде вычета на взнос в следующем " +
                "налоговом периоде. Не упустите возможность разнообразить свой портфель, " +
                "снизить риски и следить за актуальными рыночными тенденциями. " +
                "Откройте ИИС сегодня и станьте ближе к финансовой независимости!";
    }

    @Override
    public Optional<RecommendationRuleSet> checkRecommendation(UUID userId) {
        // было бы здорово вынести типы транзакций в enum, например
        boolean isUserUsedDeposit = repository.isUserUsedTransactionType(userId, "DEBIT");
        boolean isUserNotUsedInvest = !repository.isUserUsedTransactionType(userId, "INVEST");
        boolean isUserHaveEnoughSumAmountOfTransactionsByProduct =
                repository.sumAmountOfTransactionsByProduct(
                        userId, "SAVING", "DEPOSIT") > 1000;

        return isUserUsedDeposit && isUserNotUsedInvest && isUserHaveEnoughSumAmountOfTransactionsByProduct ?
                Optional.of(this) :
                Optional.empty();
    }
}
