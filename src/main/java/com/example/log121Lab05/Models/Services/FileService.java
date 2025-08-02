/*
 * @Author: mikey.zhaopeng
 * @Email:  admin@example.com
 * @Date:   2016-07-29 15:57:29
 * @Last Modified by: Noscere
 * @Last Modified time: 2022-11-15 06:25:53.546
 * @Description: description
 */
package com.example.log121Lab05.Models.Services;

import com.example.log121Lab05.Models.State;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.DateFormat;
import java.util.Date;

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
