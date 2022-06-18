package com.example;

import com.example.beans.Person;
import com.example.beans.Vehicle;
import com.example.config.ProjectConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Example15 {

    public static void main(String[] args) {

        try(var context = new AnnotationConfigApplicationContext(ProjectConfig.class)) {
            Person person = context.getBean(Person.class);
            System.out.println("Person created by spring: " + person.getName());
            Vehicle vehicle = person.getVehicle();
            System.out.println("Vehicle that person owns: " + person.getVehicle());
            vehicle.getService().move();
            vehicle.getService().playMusic();
        }
    }
}
