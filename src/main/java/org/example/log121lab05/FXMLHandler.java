package org.example.log121lab05;


import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

public class FXMLHandler
{
    private static FXMLHandler h = null;

    @FXML
    private ImageView imageView1;
    @FXML
    private ImageView imageView2;
    @FXML
    private ImageView imageView3;

    private FXMLHandler()
    {
        h = this;
    }

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
        // todo: figure this out
        Platform.runLater(() -> {
            imageView1.setImage(SwingFXUtils.toFXImage(img1, null));
            imageView2.setImage(SwingFXUtils.toFXImage(img2, null));
            imageView3.setImage(SwingFXUtils.toFXImage(img3, null));
        });
    }
}
