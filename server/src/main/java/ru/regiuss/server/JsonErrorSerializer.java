package ru.regiuss.server;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import ru.regiuss.server.exception.CustomException;

import java.io.IOException;

public class JsonErrorSerializer extends JsonSerializer<CustomException> {
    @Override
    public void serialize(CustomException e, JsonGenerator generator, SerializerProvider provider) throws IOException {
        generator.writeStartObject();
        generator.writeStringField("message", e.getMessage());
        generator.writeNumberField("status", e.getStatus());
        generator.writeEndObject();
    }
}
