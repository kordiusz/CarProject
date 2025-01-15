package com.example.carprojectfr.models;

import javafx.geometry.Point2D;

public class Car {
    private String model;
    private String registrationNumber;
    private double weight;
    private double speed;

    private Engine engine;
    private Gearbox gearbox;
    private Clutch clutch;

    public Car(String model, String registrationNumber, double weight, double speed) {
        this.model = model;
        this.registrationNumber = registrationNumber;
        this.weight = weight;
        this.speed = speed;
    }

    public Car(){}

    // Getters and setters
    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public String getRegistrationNumber() { return registrationNumber; }
    public void setRegistrationNumber(String registrationNumber) { this.registrationNumber = registrationNumber; }

    public double getWeight() { return weight; }
    public void setWeight(double weight) { this.weight = weight; }

    public double getSpeed() { return speed; }
    public void setSpeed(double speed) { this.speed = speed; }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public Gearbox getGearbox() {
        return gearbox;
    }

    public void setGearbox(Gearbox gearbox) {
        this.gearbox = gearbox;
    }

    public Clutch getClutch() {
        return clutch;
    }

    public void setClutch(Clutch clutch) {
        this.clutch = clutch;
    }

    @Override
    public String toString() {
        return model;
    }


    public void driveTo(Point2D destination) {

    }
}

