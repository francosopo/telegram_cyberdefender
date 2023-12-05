package org.example;

import org.config.ConfigService;
import org.http.MyHttpClient;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.HashMap;

public class Bot extends TelegramLongPollingBot {
    private MyHttpClient httpClient;
    private ConfigService configService;

    Bot()
    {
        this.httpClient = new MyHttpClient();
        this.configService = ConfigService.getConfigService();
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
        var user = msg.getFrom();
        var id = user.getId();
        System.out.println(user.getFirstName() + " wrote " + msg.getText());
        HashMap<String, String> messageToVerify = new HashMap<String, String>();
        messageToVerify.put("message", msg.getText());

        boolean response = this.httpClient.post(this.configService.getParameter("URL_FILTER"), messageToVerify);
        System.out.println("Sending text to analize...");

        if (response)
        {
            sendText(id, "Su hijo fue victima de lenguaje ofensivo");
        }
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
