package ru.regiuss.server.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ru.regiuss.server.JsonErrorSerializer;

@JsonSerialize(using = JsonErrorSerializer.class)
public class CustomException extends RuntimeException{

    protected final int status;
    protected final String description;

    public CustomException() {
        super("Unknown error");
        this.description = "";
        this.status = 1;
    }

    public CustomException(int status, String message) {
        super(message);
        this.status = status;
        this.description = message;
    }

    public CustomException(int status, String message, String description) {
        super(message);
        this.status = status;
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }
}
