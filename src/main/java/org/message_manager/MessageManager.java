package org.message_manager;

import org.checkerframework.checker.units.qual.A;
import org.commands.CommandFactory;
import org.commands.RecordChildren;
import org.commands.RecordResponsibles;
import org.commands.SendResponsibleAndChildren;
import org.commands.interfaces.ICommand;
import org.message_manager.interfaces.IMessageManager;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.ArrayList;
import java.util.HashMap;

public class MessageManager implements IMessageManager {
    private final CommandFactory commandsFactory;
    private final HashMap<Long, ArrayList<Long>> responsibles;
    private final HashMap<Long, ArrayList<Long>> children;
    public MessageManager()
    {
        this.commandsFactory = new CommandFactory();
        this.commandsFactory.addCommand("addParent", new RecordResponsibles());
        this.commandsFactory.addCommand("addChild", new RecordChildren());
        this.commandsFactory.addCommand("end", new SendResponsibleAndChildren());
        this.responsibles = new HashMap<>();
        this.children = new HashMap<>();
    }
    @Override
    public void execute(Message msg) {
        ICommand command = this.commandsFactory.getCommand(msg);
        command.executeCommand(msg, this);
    }

    public HashMap<Long, ArrayList<Long>> getChildren() {
        return children;
    }

    private void addToMap(HashMap<Long, ArrayList<Long>> map, Long key, String value)
    {
        if (!map.containsKey(key))
        {
            map.put(key, new ArrayList<>());
        }
        map.get(key).add(Long.parseLong(value));
    }
    public void addResponsible(Long userID, String responsible)
    {
        this.addToMap(this.responsibles, userID, responsible);
    }

    public void addChild(Long userId, String child)
    {
        this.addToMap(this.children,userId, child);
    }

    private ArrayList<Long> getFromList(HashMap<Long, ArrayList<Long>> map, Long key)
    {
        if (map.containsKey(key))
        {
            return map.get(key);
        }
        return null;
    }

    public ArrayList<Long> getResponsibleList(Long userId)
    {
        return this.getFromList(this.responsibles, userId);
    }

    public ArrayList<Long> getChildrenList(Long userId)
    {
        return this.getFromList(this.children, userId);
    }
}
