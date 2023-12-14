package org.example;
import org.commands.interfaces.IFactory;
import org.config.ConfigService;
import org.example.interfaces.IBot;
import org.message_manager.MessageManager;
import org.message_manager.interfaces.IMessageManager;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


public class Bot extends TelegramLongPollingBot implements IBot {
    private ConfigService configService;
    private IMessageManager messageManager;

    Bot()
    {
        this.configService = ConfigService.getConfigService();
        this.messageManager = new MessageManager();

    }

    @Override
    public String getBotUsername() {
        return "Cyberdefender";
    }

    @Override
    public String getBotToken() {
        return this.configService.getParameter("BOT_TOKEN");
    }

    @Override
    public void onUpdateReceived(Update update) {
        var msg = update.getMessage();
        System.out.println("Message received");
        this.messageManager.execute(msg, this);
    }

    public void sendText(Long who, String what)
    {
        if (who == null)
        {
            throw new RuntimeException("who is null");
        }
        if (what == null)
        {
            throw new RuntimeException("what is null");
        }
        SendMessage sm = SendMessage.builder()
                        .chatId(who.toString())
                .text(what).build();
        try {
            execute(sm);
        }catch(TelegramApiException e)
        {
            throw new RuntimeException(e);
        }
    }
}
