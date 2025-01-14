package com.example.carprojectfr;

import com.example.carprojectfr.models.Car;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class CarViewController
{


    public TextField modelField;
    public TextField registrationField;
    public TextField weightField;
    public TextField speedField;

    public TextField gearboxNameField;
    public TextField gearboxPriceField;
    public TextField gearboxWeightField;
    public TextField gearboxGearField;

    public TextField engineNameField;
    public TextField enginePriceField;
    public TextField engineWeightField;
    public TextField engineRpmField;

    public TextField clutchNameField;
    public TextField clutchPriceField;
    public TextField clutchWeightField;
    public TextField clutchConditionField;

    private static final ArrayList<Car> cars = new ArrayList<>();

    public static void addCar(Car c){
        cars.add(c);
    }

    public void getComponentsDataFromView(){



    }
}
