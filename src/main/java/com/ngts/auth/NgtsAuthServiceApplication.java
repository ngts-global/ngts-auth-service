package com.ngts.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.ngts.common.*", "com.ngts.auth.*"})
public class NgtsAuthServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(NgtsAuthServiceApplication.class, args);
    }
}
