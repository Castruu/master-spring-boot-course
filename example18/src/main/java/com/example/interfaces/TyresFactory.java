package com.example.interfaces;

import com.example.implementations.BridgeStoneTyres;
import com.example.implementations.MichelinTyres;

public class TyresFactory {

    public static Tyres createMichelinTyres() {
        return new MichelinTyres();
    }

    public static Tyres createBridgeStoneTyres() {
        return new BridgeStoneTyres();
    }

}
