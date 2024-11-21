package org.sky_pro.team_work.bot;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.sky_pro.team_work.bot.command.Command;
import org.sky_pro.team_work.bot.command.CommandKeeper;
import org.sky_pro.team_work.bot.configuration.BotConfig;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
@Slf4j
public class RecommendationBot extends TelegramLongPollingBot implements BotCommands {
    private final BotConfig botConfig;
    private final CommandKeeper commandKeeper;

    public RecommendationBot(BotConfig botConfig, CommandKeeper commandKeeper) {
        super(botConfig.getBotToken());
        this.botConfig = botConfig;
        this.commandKeeper = commandKeeper;
    }

    @PostConstruct
    public void postConstruct() {
        try {
            this.execute(new SetMyCommands(LIST_OF_COMMANDS, new BotCommandScopeDefault(), null));
        } catch (TelegramApiException e) {
            log.error("Error setting bot's command list: {}", e.getMessage());
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
        log.info("Update was received: {}", update);

        if (update.hasMessage() && update.getMessage().hasText()) {
            String receivedText = update.getMessage().getText();
            Command command = commandKeeper.getCommand(receivedText);
            try {
                execute(command.perform(update));
            } catch (TelegramApiException e) {
                log.error("Error executing bot message: {}", e.getMessage());
            }
        }
    }

    @Override
    public String getBotUsername() {
        return botConfig.getBotUserName();
    }
}
