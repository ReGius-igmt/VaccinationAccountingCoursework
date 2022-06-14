package ru.regiuss.client.api;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class URIBuilder {
    private String host;
    private final Map<String, String> params;

    public URIBuilder(String host){
        this.params = new HashMap<>();
        this.host = host;
    }

    public URIBuilder param(String key, Object value){
        params.put(key, value.toString());
        return this;
    }

    public URI build(){
        StringBuilder builder = new StringBuilder(host);
        if(!params.isEmpty()){
            builder.append("?");
            params.forEach((key, value) -> builder.append(key).append("=").append(value).append("&"));
            builder.deleteCharAt(builder.length()-1);
        }
        return URI.create(builder.toString());
    }
}
