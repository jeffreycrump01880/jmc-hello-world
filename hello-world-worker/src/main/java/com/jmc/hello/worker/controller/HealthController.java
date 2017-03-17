package com.jmc.hello.worker.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @RequestMapping("/healthcheck")
    public String getHealthStatus() {
        return "HEALTHY";
    }

}