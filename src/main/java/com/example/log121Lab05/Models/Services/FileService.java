/*
 * @Author: Yvon Meunier, Aymerik Blais, Simon Boudreau
 * @Date:   2025-07-21 20:00:00
 * @Last Modified by: Yvon Meunier
 * @Last Modified time: 2025-08-02 20:00:00
 */
package com.example.log121Lab05.Models.Services;

import com.example.log121Lab05.Models.State;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.DateFormat;
import java.util.Date;

/**
 * FileService is a class which acts as a wrapper on top of the File API. Its purpose is to simply read and write files
 */
public class FileService {

    /**
     * Reads the data of the image file on the disk and extracts the BufferedImage from it
     * @param file the image file
     * @return The image
     */
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

    /**
     * Serializes the current state and creates a save file
     * @param state the current state
     */
    public static void writeSave(State state) {
        try {
            File dir = new File("tmp/test");
            dir.mkdirs();
            File file = new File(dir,"save.ser");
            file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(state);
            oos.flush();
            oos.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Loads the State from the save file by deserializing the data
     * @param file the save file
     * @return the state
     */
    public static State loadSave(File file) {
        try {
            FileInputStream fis = new FileInputStream(file.getPath());
            ObjectInputStream ois = new ObjectInputStream(fis);
            State state = (State) ois.readObject();
            ois.close();
            return state;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
