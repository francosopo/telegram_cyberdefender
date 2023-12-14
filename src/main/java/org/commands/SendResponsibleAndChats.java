package org.commands;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.commands.interfaces.ICommand;
import org.message_manager.interfaces.IMessageManager;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;

public class SendResponsibleAndChats extends AbstractCommand implements ICommand {

    public SendResponsibleAndChats()
    {
        super();
    }
    @Override
    public int executeCommand(Long userId, String msg, IMessageManager msgManager) {
        ArrayList<Long> responsible = msgManager.getResponsibleList(userId);
        ArrayList<Long> chats = msgManager.getChatList(userId);
        System.out.println("Sending data");

        if (responsible == null)
        {
            System.out.println("Cannot send data, responsibles are null");
            this.setMessage(userId, "No hay responsables");
            return -1;
        }

        if (chats == null)
        {
            System.out.println("Cannot send data, chats are null");
            this.setMessage(userId, "No hay chats registrados");
            return -1;
        }

        ObjectMapper mapper = new ObjectMapper();
        HashMap<String, ArrayList<Long>> data = new HashMap<>();
        data.put("responsables", responsible);
        data.put("chats", chats);
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
                this.setMessage(userId, "No se pudo registrar, intente más tarde");
                return -1;
            }
            System.out.println("Recorded!");
            this.setMessage(userId, "Registrados satisfactoriamente");
            msgManager.clean(userId);
            return 0;
        } catch (JsonProcessingException e) {
            System.out.println("Could not make JSON object " + e);
            return -2;
        } catch (IOException e) {
            System.out.println("Could not make request from I/O "+ e);
            return -3;
        } catch (InterruptedException e) {
            System.out.println("Could not make request from Network "+ e);
            return -4;
        }
    }
}
