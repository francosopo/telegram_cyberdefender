package org.commands;

import org.commands.interfaces.ICommand;
import org.message_manager.interfaces.IMessageManager;

public class RecordChats extends AbstractCommand implements ICommand {
    @Override
    public int executeCommand(Long id, String msg, IMessageManager msgManager) {
        if (msg == null)
        {
            this.setMessage(id, "Provea un identificador");
            return -1;
        }
        msgManager.addChild(id, msg);
        System.out.println("Chat added");
        this.setMessage(id, "Chat agregado(a)");
        return 0;
    }


}
