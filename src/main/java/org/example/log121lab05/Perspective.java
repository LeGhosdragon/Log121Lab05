package org.example.log121lab05;

import java.awt.*;

public class Perspective implements IObservable
{
    private Point pointUL = new Point(0,0);
    private Point pointDR = new Point(512,512);
    private double zoomLevel = 1.0;
    private int viewIndex;

    public Perspective(int index)
    {
        viewIndex = index;
    }

    public void zoom(double zoomFactor, Point zoomCenter) {
        zoomLevel = zoomLevel * zoomFactor;

        int width = pointDR.x - pointUL.x;
        int height = pointDR.y - pointUL.y;

        // New width/height after zoom
        int newWidth = (int)(width / zoomFactor);
        int newHeight = (int)(height / zoomFactor);

        double ratioX = (zoomCenter.x - pointUL.x) / (double) width;
        double ratioY = (zoomCenter.y - pointUL.y) / (double) height;

        int newULx = zoomCenter.x - (int)(newWidth * ratioX);
        int newULy = zoomCenter.y - (int)(newHeight * ratioY);
        int newDRx = newULx + newWidth;
        int newDRy = newULy + newHeight;

        pointUL = new Point(newULx, newULy);
        pointDR = new Point(newDRx, newDRy);

        notifyObservators();
    }


    public double getZoomLevel() {
        return zoomLevel;
    }


    public int getViewIndex()
    {
        return viewIndex;
    }

    public void translate(double deltaX, double deltaY) {
        int newULx = pointUL.x - (int) deltaX;
        int newULy = pointUL.y - (int) deltaY;
        int newDRx = pointDR.x - (int) deltaX;
        int newDRy = pointDR.y - (int) deltaY;

        int imgWidth = Image.getInstance().getImageWidth();
        int imgHeight = Image.getInstance().getImageHeight();


        int viewportWidth = pointDR.x - pointUL.x;
        int viewportHeight = pointDR.y - pointUL.y;


        if (newULx < 0) {
            newULx = 0;
            newDRx = viewportWidth;
        } else if (newDRx > imgWidth) {
            newDRx = imgWidth;
            newULx = imgWidth - viewportWidth;
        }

        if (newULy < 0) {
            newULy = 0;
            newDRy = viewportHeight;
        } else if (newDRy > imgHeight) {
            newDRy = imgHeight;
            newULy = imgHeight - viewportHeight;
        }

        pointUL = new Point(newULx, newULy);
        pointDR = new Point(newDRx, newDRy);

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


    private void notifyObservators()
    {
        for (Observator obs: Observators)
        {
            obs.update(this);
        }
    }
}
