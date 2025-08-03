/*
 * @Author: Yvon Meunier, Aymerik Blais, Simon Boudreau
 * @Date:   2025-07-21 20:00:00
 * @Last Modified by: Yvon Meunier
 * @Last Modified time: 2025-08-02 20:00:00
 */
package com.example.log121Lab05.Models;

import com.example.log121Lab05.Models.Commands.PasteType;
import com.example.log121Lab05.Observable;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Observer;

/**
 * State is a class which holds all the data representing the state of our application such as the current image and the state of the perspectives like the zoom or the translations
 */
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

    /**
     * Creates a backup which can be loaded
     * @return
     */
    public Memento createMemento() {
        return new Memento(this);
    }

    /**
     * Loads the backup
     * @param m
     */
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

    /**
     * Sets all the parameters of this instance to what is currently passed as a parameter. This is done typically when loading a save file
     * @param state the desired state
     */
    public void setState(State state) {
        this.perspective1 = new Perspective(state.perspective1);
        this.perspective2 = new Perspective(state.perspective2);
        this.image = state.image;
        notifyObservers(state);
    }

    /**
     * Asks the perspective to translate the image inside it by a certain amount in a certain direction
     * @param perspective
     * @param deltaX
     * @param deltaY
     */
    public void translatePerspective(Perspective perspective, double deltaX, double deltaY) {
        perspective.translate(deltaX, deltaY, image.getWidth(), image.getHeight());
        notifyObservers(perspective);
    }

    /**
     * Asks the perspective to zoom on the image at a certain point, typically the mouse cursor location
     * @param perspective
     * @param zoomFactor
     * @param zoomCenter
     */
    public void zoomPerspective(Perspective perspective, double zoomFactor, Point zoomCenter) {
        perspective.zoom(zoomFactor, zoomCenter);
        notifyObservers(perspective);
    }

    /**
     * Asks the perspective to paste onto itself the pasteBoard values
     * @param target
     * @param pasteBoard
     * @param type
     */
    public void pastePerspective(Perspective target, Perspective pasteBoard, PasteType type) {
        switch(type){
            case ZOOM:
                double zoomLevel = target.getZoomLevel();
                zoomLevel = 1.0 / zoomLevel;
                zoomLevel *= pasteBoard.getZoomLevel();
                target.zoom(zoomLevel, target.getPointUL());
                break;

            case POSITION:
                Point dr = target.getPointDR();
                Point ul = target.getPointUL();
                Point delta = new Point(dr.x - ul.x, dr.y - ul.y);

                Point newUL = pasteBoard.getPointUL();
                Point newDR = new Point(newUL.x + delta.x, newUL.y + delta.y);

                target.setPointUL(newUL);
                target.setPointDR(newDR);
                break;

            case ALL:
                target.setPointDR(pasteBoard.getPointDR());
                target.setPointUL(pasteBoard.getPointUL());
                break;
        }

        notifyObservers(target);
    }

    /**
     * Serializes this instance and specifies how to handle the BufferedImage
     * @param oos
     * @throws IOException
     */
    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject();
        ImageIO.write(image, "png", oos);
    }

    /**
     * Deserializes the given object and specifies how to read the BufferedImage
     * @param ois
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject();
        this.image = ImageIO.read(ois);
    }

}
