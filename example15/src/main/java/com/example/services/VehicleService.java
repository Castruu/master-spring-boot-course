package com.example.services;

import com.example.interfaces.Speakers;
import com.example.interfaces.Tyres;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleService {

    private Tyres tyres;
    private Speakers speakers;

    public void playMusic() {
        speakers.makeSound();
    }

    public void move() {
        System.out.println(tyres.rotate());
    }


    public Tyres getTyres() {
        return tyres;
    }
    @Autowired
    public void setTyres(Tyres tyres) {
        this.tyres = tyres;
    }

    public Speakers getSpeakers() {
        return speakers;
    }
    @Autowired
    public void setSpeakers(Speakers speakers) {
        this.speakers = speakers;
    }
}
