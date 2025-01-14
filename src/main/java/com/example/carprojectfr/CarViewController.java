package com.example.carprojectfr;

import com.example.carprojectfr.models.Car;
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
        //carComboBox.setItems(FXCollections.observableArrayList(cars));
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
