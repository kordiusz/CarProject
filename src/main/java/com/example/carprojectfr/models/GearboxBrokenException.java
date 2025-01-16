package com.example.carprojectfr.models;

public class GearboxBrokenException extends Throwable {
    @Override
    public String getMessage() {
        return "Zmiana biegu przy przycisnietym sprzegle! Skrzynia kaput!";
    }
}
