package org.http;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import com.fasterxml.jackson.databind.ObjectMapper;

public class MyHttpClient {

    private final HttpClient httpClient;
    public MyHttpClient()
    {
        httpClient = HttpClient.newHttpClient();
    }
    public boolean post(String url, Map<String, String> data)
    {
        try
        {
            ObjectMapper obj = new ObjectMapper();
            String json = obj.writeValueAsString(data);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .headers("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();
            HttpResponse<String> response = this.httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());

            ObjectMapper jsonResponseMapper = new ObjectMapper();
            HashMap<String, String> map = jsonResponseMapper.readValue(response.body(), HashMap.class);
            return !map.get("class_name").equals("NO");

        }catch (Exception e)
        {
            System.out.println("Cannot send data: "+ e.toString());
            return false;
        }

    }
}
