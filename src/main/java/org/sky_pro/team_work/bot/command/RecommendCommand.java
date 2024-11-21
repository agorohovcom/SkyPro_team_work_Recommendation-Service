package org.sky_pro.team_work.bot.command;

import lombok.RequiredArgsConstructor;
import org.sky_pro.team_work.bot.BotService;
import org.sky_pro.team_work.bot.dto.UserDto;
import org.sky_pro.team_work.domain.Recommendation;
import org.sky_pro.team_work.service.RecommendationsService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RecommendCommand implements Command {
    private final BotService botService;
    private final RecommendationsService recommendationsService;

    @Override
    public SendMessage perform(Update update) {
        long chatId = update.getMessage().getChatId();
        String[] receivedText = update.getMessage().getText().split(" ");

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);

        if (receivedText.length == 2) {
            String username = receivedText[1];
            List<UserDto> users = botService.findByUsername(username);
            if (users.size() == 1) {
                UserDto user = users.getFirst();
                user.setRecommendations(recommendationsService.getRecommendations(user.getId()));
                sendMessage.setText(getFormattedText(user));
            } else {
                sendMessage.setText("Пользователь не найден");
            }
        } else {
            sendMessage.setText("Чтобы получить рекомендацию, введите:\n\"/recommend + <ваш_логин>\"");
        }

        return sendMessage;
    }

    @Override
    public String getCommandName() {
        return "/recommend";
    }

    private String getFormattedText(UserDto user) {
        StringBuilder recommendations = new StringBuilder();
        for (Recommendation r : user.getRecommendations()) {
            recommendations
                    .append("\n∎ ")
                    .append(r.getName())
                    .append("\n\n")
                    .append(r.getText())
                    .append("\n");
        }
        return String.format("""
                Здравствуйте, %s %s.
                
                Новые продукты для вас:
                %s
                """, user.getFirstName(), user.getLastName(), recommendations);
    }
}
