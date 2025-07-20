package org.example.log121lab05;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;

public class State implements Serializable
{
    private static State currentState = null;
    private ArrayList<Perspective> perspectives = new ArrayList<>();
    private Image image = null;

    public State(Perspective persp1, Perspective persp2, Image image)
    {
        this.perspectives.add(persp1);
        this.perspectives.add(persp2);
        this.image = image;
    }

    public void setState(State state)
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

    public State getState()
    {
        return currentState;
    }

    @Serial
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        // Serialize image as PNG since BufferedImage isnt serializable
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
