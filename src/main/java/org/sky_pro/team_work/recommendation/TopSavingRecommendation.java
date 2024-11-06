package org.sky_pro.team_work.recommendation;

import lombok.RequiredArgsConstructor;
import org.sky_pro.team_work.repository.RecommendationsRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class TopSavingRecommendation extends Recommendation {

    private final RecommendationsRepository repository;

    {
        this.id = UUID.fromString("59efc529-2fff-41af-baff-90ccd7402925");
        this.name = "Top Saving";
        this.text = "Откройте свою собственную «Копилку» с нашим банком! «Копилка» — это уникальный " +
                "банковский инструмент, который поможет вам легко и удобно накапливать деньги на важные цели. " +
                "Больше никаких забытых чеков и потерянных квитанций — всё под контролем!\n" +
                "\n" +
                "Преимущества «Копилки»:\n" +
                "\n" +
                "Накопление средств на конкретные цели. Установите лимит и срок накопления, и банк будет " +
                "автоматически переводить определенную сумму на ваш счет.\n" +
                "\n" +
                "Прозрачность и контроль. Отслеживайте свои доходы и расходы, контролируйте процесс накопления " +
                "и корректируйте стратегию при необходимости.\n" +
                "\n" +
                "Безопасность и надежность. Ваши средства находятся под защитой банка, а доступ к ним возможен " +
                "только через мобильное приложение или интернет-банкинг.\n" +
                "\n" +
                "Начните использовать «Копилку» уже сегодня и станьте ближе к своим финансовым целям!";
    }

    @Override
    public Optional<RecommendationRuleSet> checkRecommendation(UUID userId) {
        // было бы здорово вынести типы транзакций в enum, например
        boolean isUserUsedDeposit = repository.isUserUsedTransactionType(userId, "DEBIT");
        boolean isUserHaveEnoughSumAmountOfTransactionsByOneProduct =
                repository.sumAmountOfTransactionsByProduct(
                        userId,
                        "DEBIT",
                        "DEPOSIT") >= 50_000 ||
                        repository.sumAmountOfTransactionsByProduct(
                                userId,
                                "SAVING",
                                "DEPOSIT") >= 50_000;
        boolean isDepositDebitSumMoreThenWithdrawDebitSum =
                repository.sumAmountOfTransactionsByProduct(
                        userId,
                        "DEBIT",
                        "DEPOSIT") >
                        repository.sumAmountOfTransactionsByProduct(
                                userId,
                                "DEBIT",
                                "WITHDRAW");

        return isUserUsedDeposit &&
                isUserHaveEnoughSumAmountOfTransactionsByOneProduct &&
                isDepositDebitSumMoreThenWithdrawDebitSum ?
                Optional.of(this) :
                Optional.empty();
    }
}
