package org.example.log121lab05;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application
{
    @Override
    public void start(Stage stage) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("program-view.fxml"));
        fxmlLoader.setController(Controller.getInstance());
        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root, 1280, 720);
        stage.setTitle("Lab5");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args)
    {
        launch();
    }
}