package org.example.log121lab05;

import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import org.example.log121lab05.Models.Image;
import org.example.log121lab05.Models.Perspective;

import java.awt.*;

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
        if(obs.getClass() == org.example.log121lab05.Models.Image.class)
        {
            update((org.example.log121lab05.Models.Image)obs);
        }
    }
    public void update(Perspective obs) {
        Platform.runLater(() -> {
            javafx.scene.image.Image fxImg = SwingFXUtils.toFXImage(org.example.log121lab05.Models.Image.getInstance().getImage(), null);

            // Get image-space viewport corners
            Point[] bounds = obs.getParams();
            Point topLeft = bounds[0];
            Point bottomRight = bounds[1];

            int minX = Math.min(topLeft.x, bottomRight.x);
            int minY = Math.min(topLeft.y, bottomRight.y);
            int maxX = Math.max(topLeft.x, bottomRight.x);
            int maxY = Math.max(topLeft.y, bottomRight.y);

            int imageViewportWidth = maxX - minX;
            int imageViewportHeight = maxY - minY;

            // Get StackPane (parent of the ImageView)
            ImageView view = Controller.getInstance().getImageViews()[1 + obs.getViewIndex()];
            StackPane pane = (StackPane) view.getParent();

            double paneWidth = pane.getWidth();
            double paneHeight = pane.getHeight();

            if (paneWidth == 0 || paneHeight == 0) return;

            double scaleX = (double) imageViewportWidth / paneWidth;
            double scaleY = (double) imageViewportHeight / paneHeight;

            double scale = Math.max(scaleX, scaleY);

            int viewportWidth = (int) (paneWidth * scale);
            int viewportHeight = (int) (paneHeight * scale);

            int centerX = (minX + maxX) / 2;
            int centerY = (minY + maxY) / 2;

            int newMinX = centerX - viewportWidth / 2;
            int newMinY = centerY - viewportHeight / 2;

            // Clamp to image bounds
            newMinX = (int)Math.max(0, Math.min(newMinX, fxImg.getWidth() - viewportWidth));
            newMinY = (int)Math.max(0, Math.min(newMinY, fxImg.getHeight() - viewportHeight));

            Rectangle2D viewport = new Rectangle2D(newMinX, newMinY, viewportWidth, viewportHeight);

            view.setImage(fxImg);
            view.setViewport(viewport);
        });
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
