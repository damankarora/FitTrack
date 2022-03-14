package com.example.exerciselogger.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ActivityController {

    @RequestMapping("/")
    public String home(){
        System.out.println("Hit the endpoint");
        return "Hello";
    }


}
