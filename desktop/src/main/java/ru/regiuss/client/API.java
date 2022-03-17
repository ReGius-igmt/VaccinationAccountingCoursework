package ru.regiuss.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.regiuss.client.model.Medicine;
import ru.regiuss.client.model.Reception;
import ru.regiuss.client.model.Service;
import ru.regiuss.client.model.User;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class API {
    public static List<Reception> getReceptions(Integer status){
        try {
            HttpURLConnection con = (HttpURLConnection)new URL(
                    "http://127.0.0.1:7000/api/v1/appointments?status=" + status
            ).openConnection();
            con.setRequestMethod("GET");
            con.connect();
            String resp = new String(con.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
            System.out.println(resp);
            return new ObjectMapper().readValue(resp,  new TypeReference<List<Reception>>(){});
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void saveReception(Reception reception){
        try {
            HttpURLConnection con = (HttpURLConnection)new URL(
                    "http://127.0.0.1:7000/api/v1/appointments/" + reception.getId()
            ).openConnection();
            con.setDoOutput(true);
            con.setRequestMethod("PUT");
            con.setRequestProperty("content-type", "application/json");
            String json = new ObjectMapper().writeValueAsString(reception);
            System.out.println("OBKECT: " + json);
            OutputStream os = con.getOutputStream();
            os.write(json.getBytes(StandardCharsets.UTF_8));
            os.close();
            InputStream stream = con.getResponseCode() == 200 ? con.getInputStream() : con.getErrorStream();
            String resp = new String(stream.readAllBytes(), StandardCharsets.UTF_8);
            System.out.println("SAVE: " + resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Medicine> getMedicines(Integer serviceId){
        try {
            HttpURLConnection con = (HttpURLConnection)new URL("http://127.0.0.1:7000/api/v1/services/" + serviceId).openConnection();
            con.setRequestMethod("GET");
            con.connect();
            String resp = new String(con.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
            System.out.println(resp);
            return new ObjectMapper().readValue(resp,  Service.class).getMedicines();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
