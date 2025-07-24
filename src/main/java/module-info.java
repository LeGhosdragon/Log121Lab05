module com.example.labo5carry {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.swing;
    requires java.desktop;


    opens com.example.labo5carry to javafx.fxml;
    exports com.example.labo5carry;
}