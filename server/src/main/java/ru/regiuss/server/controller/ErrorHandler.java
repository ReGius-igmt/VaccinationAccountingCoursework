package ru.regiuss.server.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.regiuss.server.exception.CustomException;

@RestControllerAdvice
@RestController
public class ErrorHandler implements ErrorController {

    //private static final String PATH = "/error";

    @ExceptionHandler(CustomException.class)
    @ResponseStatus(HttpStatus.OK)
    public CustomException handleCustomException(CustomException ce) {
        return ce;
    }

//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    @RequestMapping(value = PATH, produces = MediaType.APPLICATION_JSON_VALUE)
//    public CustomException handleError() {
//        return new CustomException(1, "Page not found");
//    }

//    public String getErrorPath() {
//        return PATH;
//    }
}
