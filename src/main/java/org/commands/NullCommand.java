package org.commands;

import org.commands.interfaces.ICommand;
import org.example.interfaces.IBot;
import org.message_manager.interfaces.IMessageManager;
import org.telegram.telegrambots.meta.api.objects.Message;

public class NullCommand extends AbstractCommand implements ICommand {

    @Override
    public int executeCommand(Long id, String msg, IMessageManager msgManager) {
        this.setMessage(id, "Comando desconocido, \nPosibles comandos: \n/addparent <id> agrega un supervisor\n/addchild <id> agrega un niño(a)\n/end finaliza");
        return -1;
    }
}
