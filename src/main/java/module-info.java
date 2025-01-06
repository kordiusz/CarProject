module com.example.carprojectfr {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.carprojectfr to javafx.fxml;
    exports com.example.carprojectfr;
}