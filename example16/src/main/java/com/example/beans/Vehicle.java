package com.example.beans;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class Vehicle {

    public Vehicle() {
        System.out.println("Vehicle bean created by Spring");
    }

    private String name = "Toyota";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void printHello() {
        System.out.println("Printing hello from Component Vehicle Bean");
    }

    @Override
    public String toString() {
        return "Vehicle name is - " + name;
    }
}
