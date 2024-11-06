package org.sky_pro.team_work.domain.products;

import org.sky_pro.team_work.domain.Recommendation;

import java.util.UUID;

public class Invest500 extends Recommendation {

    public Invest500() {
        UUID uuid = UUID.fromString("147f6a0f-3b91-413b-ab99-87f081d60d5a");
        setId(uuid);
        String name = "Invest500";
        setName(name);
        String message = """
                Откройте свой путь к успеху с индивидуальным инвестиционным счетом (ИИС) от
                нашего банка! Воспользуйтесь налоговыми льготами и начните инвестировать с умом. Пополните счет до конца года и
                получите выгоду в виде вычета на взнос в следующем налоговом периоде. Не упустите возможность разнообразить свой
                портфель, снизить риски и следить за актуальными рыночными тенденциями. Откройте ИИС сегодня и станьте ближе к
                финансовой независимости!""";
        setText(message);
    }
}
