module org.example.log121lab05 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.log121lab05 to javafx.fxml;
    exports org.example.log121lab05;
}