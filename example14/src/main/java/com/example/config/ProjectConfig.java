package com.example.config;

import com.example.beans.Vehicle;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@ComponentScan(basePackages = "com.example.beans")
public class ProjectConfig {

    @Bean
    Vehicle vehicle1() {
        var vehicle = new Vehicle();
        vehicle.setName("Audi");
        return vehicle;
    }

    @Bean
    Vehicle vehicle2() {
        var vehicle = new Vehicle();
        vehicle.setName("Honda");
        return vehicle;
    }

    @Bean
    Vehicle vehicle3() {
        var vehicle = new Vehicle();
        vehicle.setName("Ferrari");
        return vehicle;
    }

}
