package org.sky_pro.team_work.bot.command;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@Slf4j
public class UserNotFoundCommand implements Command {
    @Override
    public SendMessage perform(Update update) {
        long chatId = update.getMessage().getChatId();
        String text = String.format("Пользователь не найден.\n\nВоспользуйтесь командами бота:\n%s", COMMANDS_LIST);

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(text);

        return sendMessage;
    }

    @Override
    public String getCommandName() {
        return "/user_not_found";
    }
}
