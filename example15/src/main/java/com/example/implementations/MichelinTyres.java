package com.example.implementations;

import com.example.interfaces.Tyres;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class MichelinTyres implements Tyres {
    @Override
    public int rotate() {
        System.out.println("Rotating with Michelin Tyre...");
        return 20;
    }
}
