package org.commands;

import org.commands.interfaces.ICommand;
import org.message_manager.interfaces.IMessageManager;
import org.telegram.telegrambots.meta.api.objects.Message;

public class NullCommand extends AbstractCommand implements ICommand {

    @Override
    public int executeCommand(Message msg, IMessageManager msgManager) {
        return -1;
    }
}
