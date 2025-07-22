package org.example.log121lab05;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;

public class State implements Serializable
{
    private static State currentState = new State();
    private int activePerspectiveIndex;
    private ArrayList<Perspective> perspectives = new ArrayList<>();
    private Image image = null;

    public State() {
        perspectives.add(new Perspective());
        perspectives.add(new Perspective());
        image = null;
    }

    static public void setState(State state)
    {
        for (int i = 0; i < currentState.perspectives.size(); i++)
        {
            currentState.perspectives.get(i).setParams(
                    state.perspectives.get(i).getParams()[0],
                    state.perspectives.get(i).getParams()[1]
            );
        }
        currentState.image.setImage(state.image.getImage());
    }

    static public State getState()
    {
        return currentState;
    }

    public void setActivePerspectiveIndex(int index) {
        if (index >= 0 && index < perspectives.size()) {
            this.activePerspectiveIndex = index;
        }
    }
    public Perspective getActivePerspective() {
        return perspectives.get(activePerspectiveIndex);
    }

    @Serial
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        // Serialize image as PNG since BufferedImage isn't serializable
        if (image != null) {
            ImageIO.write(image.getImage(), "png", out);
        }
    }

    @Serial
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        image.setImage(ImageIO.read(in));
    }

    public Image getImage() {
        return image;
    }
}
