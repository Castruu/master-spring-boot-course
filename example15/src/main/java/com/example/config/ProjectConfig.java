package com.example.config;

import com.example.beans.Person;
import com.example.beans.Vehicle;
import com.example.interfaces.Speakers;
import com.example.interfaces.SpeakersFactory;
import com.example.interfaces.Tyres;
import com.example.interfaces.TyresFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@ComponentScan(basePackages = {"com.example.implementations", "com.example.services"}, basePackageClasses = {Vehicle.class, Person.class})
public class ProjectConfig {

}
