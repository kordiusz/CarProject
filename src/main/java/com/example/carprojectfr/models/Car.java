package com.example.carprojectfr.models;

import javafx.geometry.Point2D;

import java.util.ArrayList;
import java.util.Objects;


public class Car extends Thread {
    private String model;
    private String registrationNumber;
    private double weight;
    private double speed;

    private Engine engine;
    private Gearbox gearbox;
    private Clutch clutch;

    private Point2D destination;
    private Point2D position = new Point2D(0,0);

    public ArrayList<Listener> listeners = new ArrayList<>();

    private final double stopTreshold = 5;
    private final double speedCalcConstant = 400;

    private boolean isRunning = false;
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

    public double getX(){return position.getX();}
    public double getY(){return position.getY();}

    @Override
    public String toString() {
        return model;
    }

    double calculateSpeed(){
        if(!clutch.isUp() || !isRunning || gearbox.isBroken())
            return 0;

        double foo =engine.getRpm() * gearbox.getGear() / speedCalcConstant;
        return foo;
    }

    public void gearUp() throws GearboxBrokenException {
        if(clutch.isUp()){
            throw new GearboxBrokenException();
        }
        gearbox.up();
        notifyListeners();
    }

    public void gearDown() throws GearboxBrokenException {
        if(clutch.isUp()){
            gearbox.setIsBroken(true);
            throw new GearboxBrokenException();
        }
        gearbox.down();
        notifyListeners();
    }

    public void clutchUp(){clutch.setIsUp(true); notifyListeners();}
    public void clutchDown(){clutch.setIsUp(false); notifyListeners();}

    @Override
    public void run(){
        double deltat = 0.1;
        while (true) {
            if (destination != null) {
                double odleglosc = Math.sqrt(Math.pow(destination.getX() - position.getX(), 2) +
                        Math.pow(destination.getY() - position.getY(), 2));
                if(odleglosc <= stopTreshold){
                    destination = null;
                    continue;
                }
                double dx = calculateSpeed() * deltat * (destination.getX() - position.getX()) /
                        odleglosc;
                double dy = calculateSpeed() * deltat * (destination.getY() - position.getY()) /
                        odleglosc;
                position = position.add(new Point2D(dx,dy));
                notifyListeners();

            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void driveTo(Point2D destination) {
        this.destination = destination;
    }

    public void accelerate(){engine.accelerate(); notifyListeners();}
    public void decelerate(){engine.deaccelerate(); notifyListeners();}

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

    @Override
    public int hashCode() {
        return Objects.hashCode(model);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Car other = (Car) obj;
        return Objects.equals(model, other.model);
    }

    public void turnOn() {
        isRunning = true;
        notifyListeners();
    }

    public void turnOff() {
        isRunning = false;
        notifyListeners();
    }

    public boolean isRunning(){return isRunning;}
}




