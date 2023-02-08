package com.example.demo.controller;

import com.example.demo.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.AccessDeniedException;

@RestController
public class TestController {

    private ScheduleService service;

    @GetMapping("/test")
    public String test() throws AccessDeniedException {
        return service.testSchedule();
    }

    @Autowired
    public TestController(ScheduleService service) {
        this.service = service;
    }
}
