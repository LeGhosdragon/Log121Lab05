package com.example.labo5carry.Models.Services;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class FileService {

    public static BufferedImage readImageFile(File file) {
        try {
            BufferedImage img = ImageIO.read(file);
            if (img != null) {
                return img;
            } else {
                System.out.println("The selected file is not a valid image.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
