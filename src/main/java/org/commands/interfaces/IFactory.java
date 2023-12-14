package org.commands.interfaces;


public interface IFactory {
    void addCommand(String name, ICommand command);
    ICommand getCommand(String cmd);
}
