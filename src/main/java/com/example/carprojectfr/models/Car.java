package com.example.carprojectfr.models;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.geometry.Point2D;

import java.util.ArrayList;
import java.util.List;


public class Car extends Thread {
    private String model;
    private String registrationNumber;
    private double weight;
    private double speed;

    private Engine engine;
    private Gearbox gearbox;
    private Clutch clutch;

    private Point2D destination;
    private Point2D position;

    public ArrayList<Listener> listeners = new ArrayList<>();

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

    public double getPredkosc(){
        return 5;
    }

    public void run(){
        double deltat = 0.1;
        while (true) {
            if (destination != null) {
                double odleglosc = Math.sqrt(Math.pow(destination.getX() - position.getX(), 2) +
                        Math.pow(destination.getY() - position.getY(), 2));
                double dx = getPredkosc() * deltat * (destination.getX() - position.getX()) /
                        odleglosc;
                double dy = getPredkosc() * deltat * (destination.getX() - position.getY()) /
                        odleglosc;
            }
            try {
                wait(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void driveTo(Point2D destination) {
        this.destination = destination;
    }


    public void addListener(Listener l) {
        listeners.add(l);
    }


    public void removeListener(Listener l) {
        listeners.remove(l);
    }

    private void notifyListeners(){
        for(Listener l : listeners){
            l.update();
        }
    }
}




