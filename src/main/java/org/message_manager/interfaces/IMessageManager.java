package org.message_manager.interfaces;

import org.example.interfaces.IBot;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.ArrayList;

public interface IMessageManager {
    void execute(Message msg, IBot bot);

    void addResponsible(Long userID, String responsible);
    void addChild(Long userId, String child);
    ArrayList<Long> getChatList(Long userId);
    ArrayList<Long> getResponsibleList(Long userId);

    void clean(Long userId);
}
