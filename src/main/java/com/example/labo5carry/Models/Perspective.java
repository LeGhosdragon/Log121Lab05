package com.example.labo5carry.Models;

import com.example.labo5carry.Controller;

import java.awt.*;

public class Perspective {

    private Point pointUL = new Point(0,0);
    private Point pointDR = new Point(512,512);
    private Point position = new  Point(256,256);
    private double zoomLevel = 1.0;
    private int viewIndex;

    public Perspective(int index)
    {
        viewIndex = index;
    }

    public Perspective(Perspective perspective) {
        this.pointUL = perspective.pointUL;
        this.pointDR = perspective.pointDR;
        this.zoomLevel = perspective.zoomLevel;
        this.viewIndex = perspective.viewIndex;
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
    }

    public void translate(double deltaX, double deltaY, int imgWidth, int imgHeight) {
        int newULx = pointUL.x - (int) deltaX;
        int newULy = pointUL.y - (int) deltaY;
        int newDRx = pointDR.x - (int) deltaX;
        int newDRy = pointDR.y - (int) deltaY;

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

        // get the coordinates of the middle of the image
        int posX = pointUL.x + imgWidth/2;
        int posY = pointUL.y + imgHeight/2;

        position = new Point(posX, posY);

        pointUL = new Point(newULx, newULy);
        pointDR = new Point(newDRx, newDRy);
    }

    public Point[] getParams()
    {
        return new Point[]{pointUL, pointDR};
    }

    public int getViewIndex(){
        return viewIndex;
    }

    public double getZoomLevel() {
        return zoomLevel;
    }

    public Point getPosition() {
        return position;
    }
}
