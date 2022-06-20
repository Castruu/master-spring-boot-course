package com.example.beans;

import com.example.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class Vehicle {


    private String name = "Toyota";
    private final VehicleService service;

    @Autowired
    public Vehicle(VehicleService service) {
        System.out.println("Vehicle created by Spring");
        this.service = service;
    }

    public VehicleService getService() {
        return service;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "Vehicle name is - " + name;
    }
}
