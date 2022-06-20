package com.example.implementations;

import com.example.interfaces.Tyres;
import org.springframework.stereotype.Component;

@Component
public class BridgeStoneTyres implements Tyres {
    @Override
    public int rotate() {
        System.out.println("Rotating with BridgeStone Tyre...");
        return 30;
    }
}