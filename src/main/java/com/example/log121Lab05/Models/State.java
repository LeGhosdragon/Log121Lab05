package com.example.log121Lab05.Models;

import com.example.log121Lab05.Observable;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Observer;

public class State extends Observable implements Serializable {

    private Perspective perspective1;
    private Perspective perspective2;
    private transient BufferedImage image;

    public State() {
        perspective1 = new Perspective(1);
        perspective2 = new Perspective(2);
    }

    public State(State state) {
        this.perspective1 = new  Perspective(state.perspective1);
        this.perspective2 = new Perspective(state.perspective2);
        this.image = state.image;
    }

    public Memento createMemento() {
        return new Memento(this);
    }

    public void loadMemento(Memento m) {
        System.out.println("RESTORE");
        State state = m.getState();

        if (state == null) return;

        this.perspective1 = state.perspective1;
        this.perspective2 = state.perspective2;
        this.image = state.image;
        notifyObservers(state);
    }

    public Perspective getPerspective1() {
        return perspective1;
    }

    public Perspective getPerspective2() {
        return perspective2;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
        notifyObservers(image);
    }

    public void setState(State state) {
        this.perspective1 = new Perspective(state.perspective1);
        this.perspective2 = new Perspective(state.perspective2);
        this.image = state.image;
        notifyObservers(state);
    }

    public void translatePerspective(Perspective perspective, double deltaX, double deltaY) {
        perspective.translate(deltaX, deltaY, image.getWidth(), image.getHeight());
        notifyObservers(perspective);
    }

    public void zoomPerspective(Perspective perspective, double zoomFactor, Point zoomCenter) {
        perspective.zoom(zoomFactor, zoomCenter);
        notifyObservers(perspective);
    }

    public void pastePerspective(Perspective target, Perspective pasteBoard) {

        Point targetPosition = target.getPosition();
        Point pastePosition = pasteBoard.getPosition();
        double targetZoomLevel = target.getZoomLevel();
        double pasteZoomLevel = pasteBoard.getZoomLevel();

        if (targetZoomLevel != pasteZoomLevel) {
            // if zoom level is different
            target.zoom(pasteZoomLevel, targetPosition);
        }

        if (!targetPosition.equals(pastePosition)) {
            int deltaX = targetPosition.x - pastePosition.x;
            int deltaY = targetPosition.y - pastePosition.y;
            target.translate(deltaX, deltaY, image.getWidth(), image.getHeight());
        }

        notifyObservers(target);
    }

    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject();
        ImageIO.write(image, "png", oos);
    }

    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject();
        this.image = ImageIO.read(ois);
    }

}
