package com.example.carprojectfr.models;

public class Clutch {
    private String name;
    private double price;
    private double weight;
    private String condition;

    private boolean isUp = false;

    public Clutch(String name, double price, double weight, String condition) {
        this.name = name;
        this.price = price;
        this.weight = weight;
        this.condition = condition;
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

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public void setIsUp(boolean isup){
        this.isUp = isup;
    }

    public boolean isUp(){return isUp;}

    @Override
    public String toString() {
        return name;
    }
}

