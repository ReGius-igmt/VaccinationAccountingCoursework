package ru.regiuss.server.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @RequestMapping("/")
    @ResponseBody
    public String home(){
        return "<h1>Hello! The Main page</h1>";
    }
}
