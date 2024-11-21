package org.sky_pro.team_work.bot;

import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;

import java.util.List;

public interface BotCommands {
    List<BotCommand> LIST_OF_COMMANDS = List.of(
            new BotCommand("/start", "приветствие"),
            new BotCommand("/recommend", "получить рекомендацию")
    );
}
