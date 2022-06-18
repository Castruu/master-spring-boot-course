package com.example;

import com.example.beans.Person;
import com.example.beans.Vehicle;
import com.example.config.ProjectConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Example13 {

    public static void main(String[] args) {

        try(var context = new AnnotationConfigApplicationContext(ProjectConfig.class)) {
            Person person = context.getBean(Person.class);
            Vehicle vehicle = context.getBean(Vehicle.class);
            System.out.println("Person name from Spring Context is: " + person.getName());
            System.out.println("Vehicle name from Spring Context is: " + vehicle.getName());
            System.out.println("Vehicle that Person owns is: " + person.getVehicle());
            System.out.println(vehicle == person.getVehicle());
        }
    }
}
