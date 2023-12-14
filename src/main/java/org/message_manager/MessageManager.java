package org.message_manager;

import org.commands.CommandFactory;
import org.commands.RecordChats;
import org.commands.RecordResponsibles;
import org.commands.SendResponsibleAndChats;
import org.commands.interfaces.ICommand;
import org.example.interfaces.IBot;
import org.message_manager.interfaces.IMessageManager;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.ArrayList;
import java.util.HashMap;

public class MessageManager implements IMessageManager {
    private final CommandFactory commandsFactory;
    private final HashMap<Long, ArrayList<Long>> responsibles;
    private final HashMap<Long, ArrayList<Long>> chats;
    public MessageManager()
    {
        this.commandsFactory = new CommandFactory();
        this.commandsFactory.addCommand("/agregarresponsable", new RecordResponsibles());
        this.commandsFactory.addCommand("/agregarpupilo", new RecordChats());
        this.commandsFactory.addCommand("/end", new SendResponsibleAndChats());
        this.responsibles = new HashMap<>();
        this.chats = new HashMap<>();
    }
    @Override
    public void execute(Message msg, IBot bot) {
        System.out.println("Called Message manager");
        String text = msg.getText();
        Long id = msg.getFrom().getId();
        String[] data = text.split(" ");
        ICommand command = this.commandsFactory.getCommand(data[0]);
        if (data.length > 1)
        {
            command.executeCommand(id,data[1], this);
        }else{
            command.executeCommand(id, null, this);
        }
        command.sendToUser(msg, bot);
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
        this.addToMap(this.chats,userId, child);
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

    @Override
    public void clean(Long userId) {
        if (this.chats.containsKey(userId) && this.responsibles.containsKey(userId)) {
            this.chats.remove(userId);
            this.responsibles.remove(userId);
            return;
        }
        System.out.println("Cannot remove, userId does not exist");
    }

    public ArrayList<Long> getChatList(Long userId)
    {
        return this.getFromList(this.chats, userId);
    }
}
