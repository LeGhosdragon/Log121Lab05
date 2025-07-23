package org.example.log121lab05;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;

public class State implements Serializable
{

    private int activePerspectiveIndex;
    private ArrayList<Perspective> perspectives = new ArrayList<>();
    private Image image = null;

    public State() {
        perspectives.add(new Perspective(0));
        perspectives.add(new Perspective(1));
        for (Perspective perspective : perspectives) {
            perspective.addObservator(Controller.getInstance());
        }
        image = null;
    }

    public void setState(State state)
    {
        for (int i = 0; i < this.perspectives.size(); i++)
        {
            this.perspectives.get(i).setParams(
                    state.perspectives.get(i).getParams()[0],
                    state.perspectives.get(i).getParams()[1]
            );
        }
        this.image.setImage(state.image.getImage());
    }


    public void setActivePerspectiveIndex(int index) {
        if (index >= 0 && index < this.perspectives.size()) {
            this.activePerspectiveIndex = index;
        }
    }
    public int getActivePerspectiveIndex() {
        return  this.activePerspectiveIndex;
    }
    public Perspective getActivePerspective() {
        return this.perspectives.get(this.activePerspectiveIndex);
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
