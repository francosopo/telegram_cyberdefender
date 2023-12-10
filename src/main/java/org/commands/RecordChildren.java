package org.commands;

import org.commands.interfaces.ICommand;
import org.message_manager.interfaces.IMessageManager;
import org.telegram.telegrambots.meta.api.objects.Message;

public class RecordChildren extends AbstractCommand implements ICommand {
    @Override
    public int executeCommand(Message msg, IMessageManager msgManager) {
        Long userId = msg.getFrom().getId();
        String data = msg.getText();
        msgManager.addChild(userId, data);
        return 0;
    }
}
