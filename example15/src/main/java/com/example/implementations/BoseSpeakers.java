package com.example.implementations;

import com.example.interfaces.Speakers;
import org.springframework.stereotype.Component;

@Component
public class BoseSpeakers implements Speakers {
    @Override
    public void makeSound() {
        System.out.println("Playing Talking To The Moon...");
    }
}
