package org.sky_pro.team_work.recommendation;

import lombok.RequiredArgsConstructor;
import org.sky_pro.team_work.repository.RecommendationsRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class EasyCreditRecommendation extends Recommendation {

    private final RecommendationsRepository repository;

    {
        this.id = UUID.fromString("ab138afb-f3ba-4a93-b74f-0fcee86d447f");
        this.name = "Простой кредит";
        this.text = "Откройте мир выгодных кредитов с нами!\n" +
                "\n" +
                "Ищете способ быстро и без лишних хлопот получить нужную сумму? " +
                "Тогда наш выгодный кредит — именно то, что вам нужно! Мы предлагаем низкие процентные ставки, " +
                "гибкие условия и индивидуальный подход к каждому клиенту.\n" +
                "\n" +
                "Почему выбирают нас:\n" +
                "\n" +
                "Быстрое рассмотрение заявки. Мы ценим ваше время, поэтому процесс рассмотрения заявки " +
                "занимает всего несколько часов.\n" +
                "\n" +
                "Удобное оформление. Подать заявку на кредит можно онлайн на нашем сайте " +
                "или в мобильном приложении.\n" +
                "\n" +
                "Широкий выбор кредитных продуктов. Мы предлагаем кредиты на различные цели: " +
                "покупку недвижимости, автомобиля, образование, лечение и многое другое.\n" +
                "\n" +
                "Не упустите возможность воспользоваться выгодными условиями кредитования от нашей компании!";
    }

    @Override
    public Optional<RecommendationRuleSet> checkRecommendation(UUID userId) {
        // было бы здорово вынести типы транзакций в enum, например
        boolean isUserNotUsedCredit = !repository.isUserUsedTransactionType(userId, "CREDIT");
        boolean isDepositDebitSumMoreThenWithdrawDebitSum =
                repository.sumAmountOfTransactionsByProduct(
                        userId,
                        "DEBIT",
                        "DEPOSIT") >
                        repository.sumAmountOfTransactionsByProduct(
                                userId,
                                "DEBIT",
                                "WITHDRAW");
        boolean isUserHaveEnoughSumAmountOfTransactionsByProduct =
                repository.sumAmountOfTransactionsByProduct(
                        userId, "DEBIT", "WITHDRAW") > 100_000;

        return isUserNotUsedCredit &&
                isDepositDebitSumMoreThenWithdrawDebitSum &&
                isUserHaveEnoughSumAmountOfTransactionsByProduct ?
                Optional.of(this) :
                Optional.empty();
    }
}
