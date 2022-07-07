package com.hotpotato.blueroof;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class BlueroofApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlueroofApplication.class, args);
    }

}
