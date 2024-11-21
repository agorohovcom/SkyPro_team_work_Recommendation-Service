package org.sky_pro.team_work.bot.command;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface Command {
    SendMessage perform(Update update);

    String getCommandName();

    String COMMANDS_LIST = "∎ /start - приветствие\n" +
            "∎ /recommend <ваш_логин> - получить рекомендацию";
}