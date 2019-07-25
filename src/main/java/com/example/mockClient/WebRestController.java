package com.example.mockClient;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
public class WebRestController {
    @RequestMapping("/answer")
    public String answer(@RequestBody String input) {
        System.out.println(input);
        return input;
    }

}
