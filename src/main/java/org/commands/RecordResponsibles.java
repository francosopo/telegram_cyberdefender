package org.commands;
import org.commands.interfaces.ICommand;
import org.message_manager.interfaces.IMessageManager;
import org.telegram.telegrambots.meta.api.objects.Message;
import java.util.HashMap;

public class RecordResponsibles extends AbstractCommand implements ICommand {

    HashMap<Long, Long[]> map;
    public RecordResponsibles()
    {
        this.map= new HashMap<>();
    }
    @Override
    public int executeCommand(Message msg, IMessageManager msgManager){
        Long userId = msg.getFrom().getId();
        String data = msg.getText();
        msgManager.addResponsible(userId, data);
        return 0;
    }
}