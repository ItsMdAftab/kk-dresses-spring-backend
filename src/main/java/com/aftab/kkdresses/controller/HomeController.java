package com.aftab.kkdresses.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "KK Dresses Spring Boot Backend Running 🚀";
    }
}