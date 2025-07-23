module org.example.log121lab05 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.swing;
    requires java.desktop;

    opens org.example.log121lab05 to javafx.fxml;
    exports org.example.log121lab05;
}
