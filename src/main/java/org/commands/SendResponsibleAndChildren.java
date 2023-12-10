package org.commands;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.commands.interfaces.ICommand;
import org.message_manager.interfaces.IMessageManager;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;

public class SendResponsibleAndChildren extends AbstractCommand implements ICommand {
    @Override
    public int executeCommand(Message msg, IMessageManager msgManager) {
        Long userId = msg.getFrom().getId();
        ArrayList<Long> responsible = msgManager.getResponsibleList(userId);
        ArrayList<Long> children = msgManager.getChildrenList(userId);

        ObjectMapper mapper = new ObjectMapper();
        HashMap<String, ArrayList<Long>> data = new HashMap<>();
        data.put("responsables", responsible);
        data.put("pupilos", children);
        try{
            String jsonData = mapper.writeValueAsString(data);
            HttpRequest req = HttpRequest.newBuilder()
                    .uri(URI.create(this.getConfigService().getParameter("RECORD_RESPONSIBLES_URI")))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonData))
                    .build();
            HttpClient client = HttpClient.newBuilder()
                    .version(HttpClient.Version.HTTP_1_1)
                    .build();

            HttpResponse<String> response = client.send(req, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.statusCode());
            System.out.println(response.body());
            int statusCode = response.statusCode();
            if (statusCode != 200)
            {
                System.out.println("Could not record");
                return -1;
            }
            System.out.println("Recorded!");
            return 0;
        } catch (JsonProcessingException e) {
            System.out.println("Could not make JSON object");
            return -2;
        } catch (IOException e) {
            System.out.println("Could not make request from I/O");
            return -3;
        } catch (InterruptedException e) {
            System.out.println("Could not make request from Network");
            return -4;
        }
    }
}
