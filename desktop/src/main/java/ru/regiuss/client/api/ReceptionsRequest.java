package ru.regiuss.client.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.regiuss.client.model.Reception;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class ReceptionsRequest implements Request<String, List<Reception>>{
    private Integer page;
    private Integer size;
    int[] laboratories;
    private Long afterDate;
    private Integer status;
    private Long beforeDate;

    private ReceptionsRequest(){}

    public static Builder builder(){
        return new Builder();
    }

    @Override
    public HttpRequest.Builder request() {
        URIBuilder builder = new URIBuilder(API.getServer() + ":" + API.getPort() + "/api/v1/receptions");
        if(page != null)builder.param("page", page);
        if(size != null)builder.param("size", size);
        if(laboratories != null)builder.param("laboratories", laboratories);
        if(afterDate != null)builder.param("afterDate", afterDate);
        if(beforeDate != null)builder.param("beforeDate", beforeDate);
        HttpRequest.Builder request = HttpRequest.newBuilder()
                .uri(builder.build())
                .GET();
        return request;
    }

    @Override
    public HttpResponse.BodyHandler<String> type() {
        return HttpResponse.BodyHandlers.ofString();
    }

    @Override
    public List<Reception> response(HttpResponse<String> response) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode n = mapper.readTree(response.body()).get("content");
            return mapper.convertValue(n, new TypeReference<>() {});
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static class Builder{

        private final ReceptionsRequest request;

        public Builder(){
            request = new ReceptionsRequest();
        }

        public Builder page(int page){
            request.page = page;
            return this;
        }

        public Builder size(int size){
            request.size = size;
            return this;
        }

        public Builder laboratories(int[] laboratories){
            request.laboratories = laboratories;
            return this;
        }

        public Builder afterDate(Long afterDate){
            request.afterDate = afterDate;
            return this;
        }

        public Builder beforeDate(Long beforeDate){
            request.beforeDate = beforeDate;
            return this;
        }

        public Builder status(int status){
            request.status = status;
            return this;
        }

        public ReceptionsRequest build(){
            return request;
        }
    }
}
