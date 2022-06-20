package com.example;

import com.example.beans.Vehicle;
import com.example.config.ProjectConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Example16 {

    public static void main(String[] args) {

        try(var context = new AnnotationConfigApplicationContext(ProjectConfig.class)) {
            Vehicle vehicle1 = context.getBean(Vehicle.class);
            Vehicle vehicle2 = context.getBean("vehicle", Vehicle.class);
            System.out.println("Hashcode of the object vehicle1: : " + vehicle1.hashCode());
            System.out.println("Hashcode of the object vehicle2: : " + vehicle2.hashCode());
            System.out.println(vehicle1 == vehicle2);
        }
    }
}
