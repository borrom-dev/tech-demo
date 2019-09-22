package com.angkorsuntrix.techdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TechDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(TechDemoApplication.class, args);
    }

}
