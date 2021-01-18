package com.anvy.petcare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(value = {"com.anvy.**"})
public class PetcareApplication {

    public static void main(String[] args) {
        SpringApplication.run(PetcareApplication.class, args);
    }

}
