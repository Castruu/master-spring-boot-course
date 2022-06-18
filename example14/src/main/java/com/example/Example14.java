package com.example;

import com.example.beans.Person;
import com.example.beans.Vehicle;
import com.example.config.ProjectConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Example14 {

    public static void main(String[] args) {

        try(var context = new AnnotationConfigApplicationContext(ProjectConfig.class)) {
            Person person = context.getBean(Person.class);
            System.out.println("Person name from Spring Context is: " + person.getName());
            System.out.println("Vehicle that Person owns is: " + person.getVehicle());
        }
    }
}
