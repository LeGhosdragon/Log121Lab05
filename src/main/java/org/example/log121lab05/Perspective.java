package org.example.log121lab05;

import java.awt.*;

public class Perspective implements IObservable
{
    private Point pointUL = new Point(0,0);
    private Point pointDR = new Point(0,0);//TODO Ajuster les params du point inferieur droit pour qu'ils suivent le rapport de grandeur de l'image HeightXWidth


    //TODO: Si on fait que ce soit des booleans en output on peux implémenter la logique de if on fail dont do X, c'est à vous de décider - Simon
//    public boolean zoom(int amount)
//    {
//
//    }

    // Il faut set les nouveaux points based on the diff between the distance from cursor to each point times
    // the amount of scrolling.
    // PS: Scroll-in = (Point)-scroll*DeltaDist and Scroll-out = (Point)sroll*DeltaDist
    //TODO:FINISH ZOOM USING WHAT IS DESCRIBED ABOVE
    public void zoom(int amount, Point mouseCoords)
    {
        //setParams();
        notifyObservators();
    }
    public void translate(int deltaX, int deltaY)
    {
        if(deltaY + pointUL.getY() <= 0)
        {
            pointUL.y = 0;
        }
        else {
            pointUL.y += deltaY;
        }
        if(deltaX + pointUL.getX() <= 0)
        {
            pointUL.x = 0;
        }
        else {
            pointUL.x += deltaX;
        }
        if(deltaY + pointDR.getY() >= Image.getInstance().getImageHeight())
        {
            pointDR.y = Image.getInstance().getImageHeight();
        }
        else {
            pointDR.y += deltaY;
        }
        if(deltaX + pointDR.getX() >= Image.getInstance().getImageWidth())
        {
            pointDR.x = Image.getInstance().getImageWidth();
        }
        else {
            pointDR.x += deltaX;
        }
        notifyObservators();
    }
    public Point[] copie()
    {
        return getParams();
    }
    public void paste(Point[] pasteBoard)
    {
        setParams(pasteBoard[0],pasteBoard[1]);
    }

    public void setParams(Point pUL, Point pDR)
    {
        pointUL = pUL;
        pointDR = pDR;
        notifyObservators();
    }

    public Point[] getParams()
    {
        return new Point[]{pointUL, pointDR};
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
