package org.example.log121lab05;

import java.awt.image.BufferedImage;

public class Image implements IObservable
{
    private BufferedImage image;
    private int imageWidth;
    private int imageHeight;

    private static Image i = null;

    private Image()
    {
        i = this;
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

    @Override
    public void notifyObservators()
    {
        for (Observator obs: Observators)
        {
            obs.update(this);
        }
    }
}
