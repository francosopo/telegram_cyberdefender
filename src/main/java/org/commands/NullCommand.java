package org.commands;

import org.commands.interfaces.ICommand;
import org.message_manager.interfaces.IMessageManager;

public class NullCommand extends AbstractCommand implements ICommand {

    @Override
    public int executeCommand(Long id, String msg, IMessageManager msgManager) {
        this.setMessage(id, "Comando desconocido, \nPosibles comandos: \n/agregarresponsable <id> agrega un supervisor\n/agregarpupilo <id> agrega un niño(a)\n/end finaliza");
        return -1;
    }
}
