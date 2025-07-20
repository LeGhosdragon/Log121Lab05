module org.example.log121lab05 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens org.example.log121lab05 to javafx.fxml;
    exports org.example.log121lab05;
}