package org.example.log121lab05.Models;

import org.example.log121lab05.Controller;
import org.example.log121lab05.IObservable;
import org.example.log121lab05.Observator;

import java.awt.image.BufferedImage;

public class Image implements IObservable
{
    private BufferedImage image;
    private int imageWidth;
    private int imageHeight;

    private static Image i = null;

    private Image()
    {
        addObservator(Controller.getInstance());
    }

    public static synchronized Image getInstance()
    {
        if (i == null)
            i = new Image();
        return i;
    }

    public void setImage(BufferedImage image)
    {
        this.image = image;
        this.imageWidth = image.getWidth();
        this.imageHeight = image.getHeight();
        notifyObservators();
    }

    public int getImageWidth()
    {
        return imageWidth;
    }
    public int getImageHeight()
    {
        return imageHeight;
    }
    public BufferedImage getImage()
    {
        return image;
    }


    @Override
    public void addObservator(Observator obs)
    {
        Observators.add(obs);
    }

    @Override
    public void removeObservator(Observator obs)
    {
        Observators.remove(obs);
    }


    private void notifyObservators()
    {
        for (Observator obs: Observators)
        {
            obs.update(this);
        }
    }
}
