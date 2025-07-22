package org.example.log121lab05;

import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

import javax.sound.sampled.Control;

public class FXMLHandler
{
    private static FXMLHandler h = null;

    private FXMLHandler() {}

    public static synchronized FXMLHandler getInstance()
    {
        if (h == null)
            h = new FXMLHandler();
        return h;
    }

    public void update(IObservable obs)
    {
        if(obs.getClass() == Perspective.class)
        {
            update((Perspective)obs);
        }
        if(obs.getClass() == Image.class)
        {
            update((Image)obs);
        }
    }
    public void update(Perspective obs)
    {
        //Perspective specific update
    }
    public void update(Image obs)
    {
        Platform.runLater(() -> {
            javafx.scene.image.Image img = SwingFXUtils.toFXImage(obs.getImage(), null);
            for(ImageView view : Controller.getInstance().getImageViews()){
                view.setImage(img);
            }
        });
    }
}
