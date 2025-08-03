/*
 * @Author: Yvon Meunier, Aymerik Blais, Simon Boudreau
 * @Date:   2025-07-21 20:00:00
 * @Last Modified by: Yvon Meunier
 * @Last Modified time: 2025-08-02 20:00:00
 * @Description: This application is a simple image "editor" were you can zoom and translate the desired image in 2 different viewports.
 * You can also go back if desired, save the project on the disk, load a save/project file and even copy-paste some elements of one perspective onto another!
 * The goal of this project is to learn how to work as a team, how to use collaboration tools such as GitHub,use the Model-View-Controller architecture, and implement various design patterns such as :
 *  - Observer
 *  - Memento
 *  - Command
 *  - Singleton
 *  - Mediator
 */
package com.example.log121Lab05;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * App entry point
 */
public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("app-view.fxml"));
        fxmlLoader.setController(Controller.getInstance());
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        stage.setTitle("LAB 5");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}