package com.example.carprojectfr.models;

import java.util.ArrayList;

public class PredefinedComponents {


    private static final PredefinedComponents instance = new PredefinedComponents();

    private PredefinedComponents(){}

    public static PredefinedComponents getInstance(){
        return instance;
    }

    public ArrayList<Engine> getEngines(){
        Engine engine1 = new Engine("Honda Civic VTEC", 3500.00, 150.0, 7000);
        Engine engine2 = new Engine("Ford EcoBoost 1.5L", 4200.00, 140.0, 6500);
        Engine engine3 = new Engine("Toyota 2JZ-GTE", 8500.00, 230.0, 7000);
        Engine engine4 = new Engine("Chevrolet LS3", 6500.00, 200.0, 6600);


        ArrayList<Engine> engines = new ArrayList<>();
        engines.add(engine1);
        engines.add(engine2);
        engines.add(engine3);
        engines.add(engine4);
        return  engines;
    }

    public ArrayList<Gearbox> getGearboxes(){
        Gearbox gearbox1 = new Gearbox("ZF 8HP Automatic", 5500.00, 85.0, 8);
        Gearbox gearbox2 = new Gearbox("Tremec TR-6060 Manual", 3500.00, 65.0, 6);
        Gearbox gearbox3 = new Gearbox("Toyota Aisin 6-Speed Auto", 4200.00, 75.0, 6);
        Gearbox gearbox4 = new Gearbox("Getrag DCT 7-Speed", 6000.00, 90.0, 7);

        // Add gearboxes to a list
        ArrayList<Gearbox> gearboxes = new ArrayList<>();
        gearboxes.add(gearbox1);
        gearboxes.add(gearbox2);
        gearboxes.add(gearbox3);
        gearboxes.add(gearbox4);
        return  gearboxes;
    }

    public ArrayList<Clutch> getClutches(){
        ArrayList<Clutch> clutches = new ArrayList<>();
        clutches.add(new Clutch("Exedy Stage 1", 350.0, 5.2, "New"));
        clutches.add(new Clutch("ACT Heavy Duty", 450.0, 6.0, "New"));
        clutches.add(new Clutch("LuK OEM Replacement", 200.0, 4.8, "New"));
        clutches.add(new Clutch("Centerforce Dual Friction", 500.0, 5.6, "New"));
        return  clutches;
    }

}
