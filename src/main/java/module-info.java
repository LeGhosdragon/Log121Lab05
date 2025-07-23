module org.example.log121lab05 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.swing;
    requires java.desktop;

    opens org.example.log121lab05 to javafx.fxml;
    exports org.example.log121lab05;
    exports org.example.log121lab05.Models;
    opens org.example.log121lab05.Models to javafx.fxml;
    exports org.example.log121lab05.Models.Services;
    opens org.example.log121lab05.Models.Services to javafx.fxml;
    exports org.example.log121lab05.Models.Commands;
    opens org.example.log121lab05.Models.Commands to javafx.fxml;
}
