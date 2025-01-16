package com.example.carprojectfr.models;

public class Gearbox {
    private String name;
    private double price;
    private double weight;
    private int gear;

    public Gearbox(String name, double price, double weight, int gear) {
        this.name = name;
        this.price = price;
        this.weight = weight;
        this.gear = gear;
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

    public int getGear() {
        return gear;
    }

    public void setGear(int gear) {
        this.gear = gear;
    }

    @Override
    public String toString() {
        return name;
    }

    public void up(){this.gear++;}
    public void down(){this.gear--;}
}

