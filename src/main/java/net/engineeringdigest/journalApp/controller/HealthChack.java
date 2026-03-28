package net.engineeringdigest.journalApp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthChack {

    @GetMapping("/health_check")
    public String healthcheck(){
        return "Ok";
    }

}
