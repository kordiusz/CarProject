package com.example.carprojectfr;

import com.example.carprojectfr.models.Car;
import com.example.carprojectfr.models.Engine;
import com.example.carprojectfr.models.Gearbox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
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

    private static ObservableList<Car> cars = FXCollections.observableArrayList();
    public ComboBox carComboBox;
    public Button add_car_btn;

    @FXML
    public void initialize(){

        carComboBox.setItems(cars);

        carComboBox.setOnAction(event->{
            refresh();
        });
        add_car_btn.setOnAction(event->{
            try {
                openAddCarWindow();
                refresh();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void refresh(){
        Car c = (Car)carComboBox.getValue();

        modelField.setText(c.getModel());
        registrationField.setText(c.getRegistrationNumber());
        weightField.setText(String.valueOf(c.getWeight()));
        speedField.setText(String.valueOf(c.getSpeed()));

        Gearbox g = c.getGearbox();
        gearboxNameField.setText(g.getName());
        gearboxPriceField.setText(String.valueOf(g.getPrice()));
        gearboxWeightField.setText(String.valueOf(g.getWeight()));
        gearboxGearField.setText(String.valueOf(g.getGear()));

        Engine e = c.getEngine();
        engineNameField.setText(e.getName());
        enginePriceField.setText(String.valueOf(e.getPrice()));
        engineRpmField.setText(String.valueOf( e.getRpm()));
        engineWeightField.setText(String.valueOf(e.getWeight()));

    }

    public static void addCar(Car c){
        cars.add(c);
    }

    public void openAddCarWindow() throws IOException {
        FXMLLoader loader = new
                FXMLLoader(getClass().getResource("AddNewCarPopup.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(loader.load()));
        stage.setTitle("Dodaj nowy samochód");
        stage.show();

    }

    public void getComponentsDataFromView(){



    }
}
