package org.commands;

import org.commands.interfaces.ICommand;
import org.commands.interfaces.IFactory;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory implements IFactory {

    private final HashMap<String, ICommand> classes;

    public CommandFactory()
    {
        this.classes = new HashMap<>();
    }

    @Override
    public void addCommand(String name, ICommand command) {
        if (!this.classes.containsKey(name))
        {
            this.classes.put(name, command);
        }
    }

    public ICommand getCommand(Message msg)
    {
        if (!this.classes.containsKey(msg.getText()))
        {
            return new NullCommand();
        }
        return this.classes.get(msg.getText());
    }
}
