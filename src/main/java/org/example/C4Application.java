package org.example; // <--- AJUSTADO

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class C4Application {
    public static void main(String[] args) {
        SpringApplication.run(C4Application.class, args);
    }
}