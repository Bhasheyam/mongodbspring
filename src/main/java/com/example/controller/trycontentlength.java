package com.example.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/findit")
public class trycontentlength {

    public String home() {
        return "hello";
    }
}
