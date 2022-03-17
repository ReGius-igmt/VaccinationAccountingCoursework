package ru.regiuss.server.exception;

public class UserNoFoundException extends CustomException{
    public UserNoFoundException(int status, String message, String description) {
        super(status, message, description);
    }
}
