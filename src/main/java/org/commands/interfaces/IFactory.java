package org.commands.interfaces;

import org.telegram.telegrambots.meta.api.objects.Message;

public interface IFactory {
    void addCommand(String name, ICommand command);
    ICommand getCommand(Message msg);
}
