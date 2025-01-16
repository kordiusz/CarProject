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
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
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
    public Button clutch_down;
    public Button clutch_up;
    public Button gear_up_btn;
    public Button gear_down_btn;
    public Button accelerate_btn;
    public Button decelerate_btn;
    public Button turn_on_btn;
    public Button turn_off_btn;
    public Label power_indicator_label;
    public Label clutch_status_label;
    public Label error_label;

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
            error_label.setText("");
        });
        add_car_btn.setOnAction(event->{
            try {
                openAddCarWindow();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        remove_car_btn.setOnAction(event->{
            car_playground.getChildren().remove(carIcons.get(getSelectedCar()));
            carIcons.remove(getSelectedCar());
            carComboBox.getItems().remove(getSelectedCar());
            refresh();
        });

        car_playground.setOnMouseClicked(event->{

            if(getSelectedCar() == null)
                return;
            double x = event.getX();
            double y = event.getY();

            getSelectedCar().driveTo(new Point2D(x,y));

        });

        //Now the buttons of each component:

        clutch_up.setOnAction(event->{
            getSelectedCar().clutchUp();
            refresh();
        });
        clutch_down.setOnAction(event->{
            getSelectedCar().clutchDown();
            refresh();
        });

        gear_up_btn.setOnAction(event->{
            try {
                getSelectedCar().gearUp();
            } catch (GearboxBrokenException e) {
                handleError(e);
            }
            refresh();
        });

        gear_down_btn.setOnAction(event->{
            try {
                getSelectedCar().gearDown();
            } catch (GearboxBrokenException e) {
                handleError(e);
            }
            refresh();
        });

        accelerate_btn.setOnAction(event->{
            getSelectedCar().accelerate();
            refresh();
        });

        decelerate_btn.setOnAction(event->{
            getSelectedCar().decelerate();
            refresh();
        });

        turn_on_btn.setOnAction(event->{
            getSelectedCar().turnOn();
            refresh();
        });
        turn_off_btn.setOnAction(event->{
            getSelectedCar().turnOff();
            refresh();
        });


    }

    Car getSelectedCar(){return (Car)carComboBox.getValue();}

    public void refresh() {

        Car c = (Car) carComboBox.getValue();
        if (c == null) {
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
        engineRpmField.setText(String.valueOf(e.getRpm()));
        engineWeightField.setText(String.valueOf(e.getWeight()));

        Clutch clutch = c.getClutch();
        clutchNameField.setText(clutch.getName());
        clutchConditionField.setText(clutch.getCondition());
        clutchPriceField.setText(String.valueOf(clutch.getPrice()));
        clutchWeightField.setText(String.valueOf(clutch.getWeight()));

        Platform.runLater(() -> {

            for (Car car : cars) {
                VBox icon = carIcons.get(car);
                icon.setTranslateX(car.getX());
                icon.setTranslateY(car.getY());
            }

        });

        if (getSelectedCar().isRunning())
            power_indicator_label.setStyle("-fx-text-fill: green; -fx-font-size: 16px;");
        else
            power_indicator_label.setStyle("-fx-text-fill: red; -fx-font-size: 16px;");

        if (clutch.isUp())
            clutch_status_label.setText("Na biegu");
        else
            clutch_status_label.setText("Na luzie");


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

        power_indicator_label.setStyle("-fx-text-fill: black; -fx-font-size: 16px;");
        clutch_status_label.setText("Na luzie");
        error_label.setText("");
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

    public void handleError(Throwable e){
        error_label.setText(e.getMessage());

    }

    @Override
    public void update() {
        Platform.runLater(this::refresh);
    }
}
