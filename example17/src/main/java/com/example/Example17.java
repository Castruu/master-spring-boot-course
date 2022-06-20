package com.example;

import com.example.beans.Person;
import com.example.beans.Vehicle;
import com.example.config.ProjectConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Example17 {

    public static void main(String[] args) {

        try(var context = new AnnotationConfigApplicationContext(ProjectConfig.class)) {
            System.out.println("Before person creation");
            Person person = context.getBean(Person.class);
            System.out.println("Person created");
            System.out.println(person.getName());
        }
    }
}
