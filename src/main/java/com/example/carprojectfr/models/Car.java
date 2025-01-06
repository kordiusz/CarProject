package com.example.carprojectfr.models;

public class Car {
    private String model;
    private String registrationNumber;
    private double weight;
    private double speed;

    public Car(String model, String registrationNumber, double weight, double speed) {
        this.model = model;
        this.registrationNumber = registrationNumber;
        this.weight = weight;
        this.speed = speed;
    }

    // Getters and setters
    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public String getRegistrationNumber() { return registrationNumber; }
    public void setRegistrationNumber(String registrationNumber) { this.registrationNumber = registrationNumber; }

    public double getWeight() { return weight; }
    public void setWeight(double weight) { this.weight = weight; }

    public double getSpeed() { return speed; }
    public void setSpeed(double speed) { this.speed = speed; }
}

