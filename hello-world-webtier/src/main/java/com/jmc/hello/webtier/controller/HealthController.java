package com.jmc.hello.webtier.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
public class HealthController {

    @RequestMapping("/healthcheck")
    @ApiOperation(value = "healthcheck")
    public String getHealthStatus() {
        return "HEALTHY";
    }

}