
module com.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;

    opens com.example to javafx.fxml;
    exports com.example;
}
