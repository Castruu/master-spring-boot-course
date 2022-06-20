package com.example.config;

import com.example.beans.Person;
import com.example.beans.Vehicle;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.example.implementations", "com.example.services"}, basePackageClasses = {Vehicle.class, Person.class})
public class ProjectConfig {

}
