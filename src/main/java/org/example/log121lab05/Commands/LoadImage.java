package org.example.log121lab05.Commands;

import org.example.log121lab05.FileHandler;
import org.example.log121lab05.ICommand;
import org.example.log121lab05.Image;
import org.example.log121lab05.State;

import java.awt.*;
import java.awt.image.BufferedImage;

public class LoadImage implements ICommand
{

    public LoadImage() {}

    @Override
    public void execute()
    {
        FileHandler fh = FileHandler.getInstance();
        BufferedImage newImg = fh.loadImage();
        if(newImg != null){
            Image.getInstance().setImage(newImg);
        }
    }
}
