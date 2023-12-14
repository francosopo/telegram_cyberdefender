package org.commands;

import org.commands.interfaces.ICommand;
import org.config.ConfigService;
import org.example.interfaces.IBot;
import org.message_manager.interfaces.IMessageManager;
import org.telegram.telegrambots.meta.api.objects.Message;
import java.util.HashMap;

public abstract class AbstractCommand implements ICommand {
    private ConfigService configService;
    private HashMap<Long, String> messages;

    AbstractCommand() {
        this.configService = ConfigService.getConfigService();
        this.messages = new HashMap<>();
    }

    public void setMessage(Long id, String msg) {
        this.messages.put(id, msg);
    }

    public String getMessage(Long id) {
        return this.messages.get(id);
    }

    public abstract int executeCommand(Long id, String arg, IMessageManager msgManager);


    public ConfigService getConfigService() {
        return this.configService;
    }

    @Override
    public int sendToUser(Message msg, IBot bot) {
        Long id = msg.getFrom().getId();
        bot.sendText(id, this.getMessage(id));
        return 0;
    }
}
