package com.example.mockClient;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class SheduledTasks {
    @Scheduled(fixedRate = 5000)
    public void scanFolder()throws Exception {

        File folder = new File("C:\\My Program Files\\myApp\\wars");

        String[] files = folder.list();

        for (String file : files) {
            System.out.println(file);
            String comand = String.format("cd \"C:\\My Program Files\\myApp\\wars\" && jar -xvf %s", file);
            ProcessBuilder builder = new ProcessBuilder("cmd.exe","/c", comand);
            Process process = builder.start();
        }


    }
}
