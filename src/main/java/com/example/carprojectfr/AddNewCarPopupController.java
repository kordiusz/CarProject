package com.example.carprojectfr;

import com.example.carprojectfr.models.Engine;
import com.example.carprojectfr.models.Gearbox;
import com.example.carprojectfr.models.PredefinedComponents;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

public class AddNewCarPopupController {
    public ComboBox engine_listview;
    public ComboBox gearbox_listview;


    @FXML
    public void initialize(){

        ObservableList<Gearbox> gearboxes = FXCollections.observableArrayList(PredefinedComponents.getInstance().getGearboxes());
        ObservableList<Engine> engines = FXCollections.observableArrayList(PredefinedComponents.getInstance().getEngines());

        gearbox_listview.setItems(gearboxes);
        engine_listview.setItems(engines);
    }
}
