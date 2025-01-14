package com.example.carprojectfr;

import com.example.carprojectfr.models.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddNewCarPopupController {
    public ComboBox engine_listview;
    public ComboBox gearbox_listview;
    public Button cancel_btn;
    public Button add_btn;
    public TextField model_field;
    public TextField registration_field;
    public TextField weight_field;
    public TextField max_speed_field;
    public ComboBox clutch_listview;


    @FXML
    public void initialize(){

        ObservableList<Gearbox> gearboxes = FXCollections.observableArrayList(PredefinedComponents.getInstance().getGearboxes());
        ObservableList<Engine> engines = FXCollections.observableArrayList(PredefinedComponents.getInstance().getEngines());
        ObservableList<Clutch> clutches = FXCollections.observableArrayList(PredefinedComponents.getInstance().getClutches());

        gearbox_listview.setItems(gearboxes);
        engine_listview.setItems(engines);
        clutch_listview.setItems(clutches);

        cancel_btn.setOnAction(actionEvent -> {
            Stage stage = (Stage)cancel_btn.getScene().getWindow();
            stage.close();
        });

        add_btn.setOnAction(event->{

            String model = model_field.getText();
            String registration = registration_field.getText();
            double weight = Double.parseDouble( weight_field.getText());
            double speed = Double.parseDouble( max_speed_field.getText());
            Car newModel = new Car(model,registration,weight,speed);

            Gearbox gearbox =  (Gearbox)gearbox_listview.getValue();
            Engine engine = (Engine) engine_listview.getValue();
            Clutch clutch = (Clutch) clutch_listview.getValue();

            newModel.setEngine(engine);
            newModel.setGearbox(gearbox);
            newModel.setClutch(clutch);
            CarViewController.addCar(newModel);

            Stage stage = (Stage)cancel_btn.getScene().getWindow();
            stage.close();
        });

    }
}
