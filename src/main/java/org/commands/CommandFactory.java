package org.commands;

import org.commands.interfaces.ICommand;
import org.commands.interfaces.IFactory;

import java.util.HashMap;

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

    public ICommand getCommand(String cmd)
    {
            if (!this.classes.containsKey(cmd))
            {
                return new NullCommand();
            }
            System.out.println("texto: " + cmd);
            return this.classes.get(cmd);
    }
}
