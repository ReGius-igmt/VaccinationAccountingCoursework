package ru.regiuss.server.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.web.bind.annotation.*;
import ru.regiuss.server.exception.CustomException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@RestControllerAdvice
@RestController
public class ErrorHandler implements ErrorController, AccessDeniedHandler {

    //private static final String PATH = "/error";

    @ExceptionHandler(CustomException.class)
    @ResponseStatus(HttpStatus.OK)
    public CustomException handleCustomException(CustomException ce) {
        return ce;
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.getOutputStream().write(new ObjectMapper().writeValueAsBytes(new CustomException(403, "Access denied")));
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
