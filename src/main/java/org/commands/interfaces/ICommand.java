package org.commands.interfaces;

import org.config.ConfigService;
import org.example.interfaces.IBot;
import org.message_manager.MessageManager;
import org.message_manager.interfaces.IMessageManager;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.net.http.HttpClient;

public interface ICommand {

    int executeCommand(Long id, String arg, IMessageManager msgManager);
    int sendToUser(Message msg, IBot bot);
    void setMessage(Long id, String msg);

    String getMessage(Long id);

    ConfigService getConfigService();

}