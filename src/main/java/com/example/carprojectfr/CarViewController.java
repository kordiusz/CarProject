package com.example.carprojectfr;

import com.example.carprojectfr.models.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
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
    public Button remove_car_btn;
    public BorderPane car_playground;

    @FXML
    public void initialize(){


        //FOR TESTING PURPOSE ONLY:
        Car example = new Car("Auto mamy", "KR 2956JW", 2000, 150);
        example.setClutch(PredefinedComponents.getInstance().getClutches().getFirst());
        example.setEngine(PredefinedComponents.getInstance().getEngines().getFirst());
        example.setGearbox(PredefinedComponents.getInstance().getGearboxes().getFirst());
        cars.add(example);
        addCarIcon(example);
        carComboBox.setItems(cars);

        carComboBox.setOnAction(event->{
            refresh();
        });
        add_car_btn.setOnAction(event->{
            try {
                openAddCarWindow();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        remove_car_btn.setOnAction(event->{
            carComboBox.getItems().remove((Car)carComboBox.getValue());
        });

        car_playground.setOnMouseClicked(event->{
            double x = event.getX();
            double y = event.getY();

            Car selected = (Car) carComboBox.getValue();
            selected.driveTo(new Point2D(x,y));
        });
    }

    public void refresh(){

        Car c = (Car)carComboBox.getValue();
        if(c == null) {
            restore_empty();
            return;
        }
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

        Clutch clutch = c.getClutch();
        clutchNameField.setText(clutch.getName());
        clutchConditionField.setText(clutch.getCondition());
        clutchPriceField.setText(String.valueOf(clutch.getPrice()));
        clutchWeightField.setText(String.valueOf(clutch.getWeight()));


    }

    private void restore_empty(){
        modelField.setText("");
        registrationField.setText("");
        weightField.setText("");
        speedField.setText("");


        gearboxNameField.setText("");
        gearboxPriceField.setText("");
        gearboxWeightField.setText("");
        gearboxGearField.setText("");

        engineNameField.setText("");
        enginePriceField.setText("");
        engineRpmField.setText("");
        engineWeightField.setText("");
        clutchNameField.setText("");
        clutchConditionField.setText("");
        clutchPriceField.setText("");
        clutchWeightField.setText("");
    }

    public static void addCar(Car c){
        cars.add(c);
    }

    void addCarIcon(Car c){


        VBox car_box = new VBox();
        car_box.setAlignment(Pos.CENTER);
        Label label = new Label(c.getModel());
        label.setStyle("-fx-font-size: 100;");
        Image image = new Image(getClass().getResource("/car.png").toExternalForm());
        ImageView img = new ImageView(image);
        img.setScaleX(0.1);
        img.setScaleY(0.1);
        car_box.getChildren().addAll(label, img);
        car_playground.getChildren().add(car_box);

        car_box.setTranslateX(100);
        car_box.setTranslateY(100);

    }

    public void openAddCarWindow() throws IOException {
        FXMLLoader loader = new
                FXMLLoader(getClass().getResource("AddNewCarPopup.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(loader.load()));
        stage.setTitle("Dodaj nowy samochód");
        stage.show();

    }

}
