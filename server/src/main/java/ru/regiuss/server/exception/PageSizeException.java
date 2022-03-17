package ru.regiuss.server.exception;

public class PageSizeException extends CustomException{
    public PageSizeException() {
        super(1, "Page size must not be less than one!");
    }
}
