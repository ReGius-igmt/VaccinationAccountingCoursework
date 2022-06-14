package ru.regiuss.client.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import ru.regiuss.client.deserializer.UserDeserializer;
import ru.regiuss.client.exception.AuthException;
import ru.regiuss.client.model.Medicine;
import ru.regiuss.client.model.Reception;
import ru.regiuss.client.model.Service;
import ru.regiuss.client.model.User;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class API {

    private final static String SERVER = "http://127.0.0.1";
    private final static String PORT = "7000";
    private byte[] authData;

    public void saveReception(Reception reception){
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(SERVER + ":" + PORT + "/api/v1/receptions/" + reception.getId()))
                    .header("Authorization", "Basic " + new String(authData, StandardCharsets.UTF_8))
                    .header("content-type", "application/json")
                    .PUT(HttpRequest.BodyPublishers.ofString(new ObjectMapper().writeValueAsString(reception))).build();
            execute(request, HttpResponse.BodyHandlers.discarding());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Medicine> getMedicines(Integer serviceId){
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(SERVER + ":" + PORT + "/api/v1/services/" + serviceId))
                .header("Authorization", "Basic " + new String(authData, StandardCharsets.UTF_8))
                .GET().build();
        HttpResponse<String> response = execute(request, HttpResponse.BodyHandlers.ofString());
        if(response == null || response.statusCode() == 401)throw new AuthException();
        try {
            return new ObjectMapper().readValue(response.body(),  Service.class).getMedicines();
        } catch (Exception e) {
            e.printStackTrace();
            throw new AuthException();
        }
    }

    public void setAuthData(byte[] authData) {
        this.authData = authData;
    }

    public User getCurrent(){
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(SERVER + ":" + PORT + "/api/v1/users/current"))
                .header("Authorization", "Basic " + new String(authData, StandardCharsets.UTF_8))
                .GET().build();
        HttpResponse<String> response = execute(request, HttpResponse.BodyHandlers.ofString());
        if(response == null || response.statusCode() == 401)throw new AuthException();
        try {
            ObjectMapper mapper = new ObjectMapper();
            SimpleModule module = new SimpleModule();
            module.addDeserializer(User.class, new UserDeserializer());
            mapper.registerModule(module);
            return mapper.readValue(response.body(), User.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new AuthException();
        }
    }

    private <T> HttpResponse<T> execute(HttpRequest request, HttpResponse.BodyHandler<T> response){
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<T> resp = client.send(request, response);
            System.out.printf("STATUS: %s %s%n", resp.statusCode(), resp.body());
            return resp;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public <R, T> T execute(Request<R, T> request){
        HttpRequest.Builder builder = request.request();
        builder.header("Authorization", "Basic " + new String(authData, StandardCharsets.UTF_8));
        HttpResponse<R> resp = execute(builder.build(), request.type());
        return request.response(resp);
    }

    public static String getServer(){
        return SERVER;
    }

    public static String getPort(){
        return PORT;
    }

}
