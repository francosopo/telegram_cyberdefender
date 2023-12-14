package org.commands;
import org.commands.interfaces.ICommand;
import org.example.interfaces.IBot;
import org.message_manager.interfaces.IMessageManager;
import org.telegram.telegrambots.meta.api.objects.Message;
import java.util.HashMap;

public class RecordResponsibles extends AbstractCommand implements ICommand {

    HashMap<Long, Long[]> map;
    public RecordResponsibles()
    {
        super();
        this.map= new HashMap<>();
    }
    @Override
    public int executeCommand(Long id, String msg, IMessageManager msgManager) {
        if (msg == null)
        {
            this.setMessage(id, "Provea un identificador");
            return -1;
        }
        msgManager.addResponsible(id, msg);
        System.out.println("Added responsible");
        this.setMessage(id, "Responsable agregado");
        return 0;
    }
}