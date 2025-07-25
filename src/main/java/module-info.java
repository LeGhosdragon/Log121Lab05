module com.example.log121Lab05 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.swing;
    requires java.desktop;


    opens com.example.log121Lab05 to javafx.fxml;
    exports com.example.log121Lab05;
}