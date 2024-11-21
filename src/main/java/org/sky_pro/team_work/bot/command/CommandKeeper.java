package org.sky_pro.team_work.bot.command;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class CommandKeeper {
    private final List<Command> commandList;
    private final Map<String, Command> commands = new HashMap<>();

    @PostConstruct
    public void init() {
        for (Command command : commandList) {
            commands.put(command.getCommandName(), command);
        }
    }

    public Command getCommand(String receivedText) {
        String commandString = receivedText.split(" ")[0];
        return commands.containsKey(commandString)
                ? commands.get(commandString)
                : commands.get("/default_command");
    }
}
