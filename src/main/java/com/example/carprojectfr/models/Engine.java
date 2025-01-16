package com.example.carprojectfr.models;

public class Engine {
    private String name;
    private double price;
    private double weight;
    private int rpm;

    private final double accelerateConstant = 100;

    public Engine(String name, double price, double weight, int rpm) {
        this.name = name;
        this.price = price;
        this.weight = weight;
        this.rpm = rpm;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getRpm() {
        return rpm;
    }

    public void setRpm(int rpm) {
        this.rpm = rpm;
    }

    @Override
    public String toString() {
        return name;
    }

    public void accelerate(){
        rpm += accelerateConstant;
    }

    public void deaccelerate(){
        rpm -= accelerateConstant;
    }
}
