package org.message_manager.interfaces;

import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.ArrayList;
import java.util.HashMap;

public interface IMessageManager {
    void execute(Message msg);
    HashMap<Long, ArrayList<Long>> getChildren();
    void addResponsible(Long userID, String responsible);
    void addChild(Long userId, String child);
    ArrayList<Long> getChildrenList(Long userId);
    ArrayList<Long> getResponsibleList(Long userId);
}
