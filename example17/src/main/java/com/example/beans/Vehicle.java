package com.example.beans;

import com.example.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component(value = "vehicleBean")
public class Vehicle {


    private String name = "Toyota";
    private final VehicleService service;

    @Autowired
    public Vehicle(VehicleService service) {
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
