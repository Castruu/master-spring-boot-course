package com.example.interfaces;


import com.example.implementations.BoseSpeakers;
import com.example.implementations.SonySpeakers;

public class SpeakersFactory {

    public static Speakers createSonySpeakers() {
        return new SonySpeakers();
    }

    public static Speakers createBoseSpeakers() {
        return new BoseSpeakers();
    }

}
