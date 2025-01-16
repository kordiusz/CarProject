package com.example.carprojectfr;

import com.example.carprojectfr.models.*;
import javafx.application.Platform;
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
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

public class CarViewController implements Listener
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
    private static Hashtable<Car, VBox> carIcons = new Hashtable<>();
    public ComboBox carComboBox;
    public Button add_car_btn;
    public Button remove_car_btn;
    public Pane car_playground;

    @FXML
    public void initialize(){


        //FOR TESTING PURPOSE ONLY:
        Car example = new Car("Auto mamy", "KR 2956JW", 2000, 150);
        example.setClutch(PredefinedComponents.getInstance().getClutches().getFirst());
        example.setEngine(PredefinedComponents.getInstance().getEngines().getFirst());
        example.setGearbox(PredefinedComponents.getInstance().getGearboxes().getFirst());
        addCar(example);


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
            Car selected = (Car) carComboBox.getValue();
            if(selected == null)
                return;
            double x = event.getX();
            double y = event.getY();

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

        Platform.runLater(() -> {

            for(Car car : cars){
                VBox icon = carIcons.get(car);
                icon.setTranslateX(car.getX());
                icon.setTranslateY(car.getY());
            }

        });
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

    public void addCar(Car c){
        cars.add(c);
        c.addListener(this);

        VBox newCarIcon = makeCarIcon(c);
        car_playground.getChildren().add(newCarIcon);
        carIcons.put(c,newCarIcon);
        c.start();
    }

    VBox makeCarIcon(Car c){


        VBox car_box = new VBox();
        car_box.setAlignment(Pos.CENTER);
        Label label = new Label(c.getModel());
        label.setStyle("-fx-font-size: 12;");
        Image image = new Image(getClass().getResource("/car_new.png").toExternalForm());
        ImageView img = new ImageView(image);

        car_box.getChildren().addAll(label, img);
        return  car_box;


    }

    public void openAddCarWindow() throws IOException {
        FXMLLoader loader = new
                FXMLLoader(getClass().getResource("AddNewCarPopup.fxml"));
        Stage stage = new Stage();
        AddNewCarPopupController controller = new AddNewCarPopupController(this);
        loader.setController(controller);
        stage.setScene(new Scene(loader.load()));
        stage.setTitle("Dodaj nowy samochód");
        stage.show();

    }

    @Override
    public void update() {
        refresh();
    }
}
