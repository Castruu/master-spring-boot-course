package com.example.implementations;

import com.example.interfaces.Speakers;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class SonySpeakers implements Speakers {
    @Override
    public void makeSound() {
        System.out.println("Playing Yellow...");
    }
}
