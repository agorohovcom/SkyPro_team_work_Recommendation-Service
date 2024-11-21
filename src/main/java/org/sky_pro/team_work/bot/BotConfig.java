package org.sky_pro.team_work.bot;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class BotConfig {
    @Value("${telegram.bot.username}")
    private String botUserName;
    @Value("${telegram.bot.token}")
    private String botToken;
}
