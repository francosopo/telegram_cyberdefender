package org.commands.interfaces;

import org.config.ConfigService;
import org.message_manager.MessageManager;
import org.message_manager.interfaces.IMessageManager;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.net.http.HttpClient;

public interface ICommand {

    int executeCommand(Message msg, IMessageManager msgManager);

    ConfigService getConfigService();

}