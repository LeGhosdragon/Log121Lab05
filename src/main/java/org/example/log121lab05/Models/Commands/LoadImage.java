package org.example.log121lab05.Models.Commands;

import org.example.log121lab05.Models.Services.FileHandler;
import org.example.log121lab05.Models.Image;

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
