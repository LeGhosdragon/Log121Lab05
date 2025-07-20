package org.example.log121lab05;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController
{
    private static HelloController c = null;

    private HelloController()
    {
        c = this;
        //this.ajouterObservateur(new );
        //this.ajouterObservateur(new );
    }

    public static synchronized HelloController getInstance()
    {
        if (c == null)
            c = new HelloController();
        return c;
    }

    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick()
    {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}