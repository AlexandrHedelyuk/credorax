package com.credorax;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class AuditApp {

    public static void main(String[] args) {
        SpringApplication.run(AuditApp.class, args);
    }
}
