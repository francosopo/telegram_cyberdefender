package org.commands;

import org.commands.interfaces.ICommand;
import org.config.ConfigService;
import org.message_manager.interfaces.IMessageManager;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.MessageAutoDeleteTimerChanged;

import java.net.http.HttpClient;

public abstract class AbstractCommand implements ICommand {
    private ConfigService configService;

    AbstractCommand()
    {
        this.configService = ConfigService.getConfigService();
    }

    public abstract int executeCommand(Message msg, IMessageManager msgManager);


    public ConfigService getConfigService()
    {
        return this.configService;
    }
}
