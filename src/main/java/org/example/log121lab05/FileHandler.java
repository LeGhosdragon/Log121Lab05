package org.example.log121lab05;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.image.BufferedImage;
import java.io.*;

public class FileHandler
{
    private static FileHandler h = null;
    private String currentFileName = null;

    private FileHandler()
    {
        h = this;
    }

    public static synchronized FileHandler getInstance()
    {
        if (h == null)
            h = new FileHandler();
        return h;
    }

    public void save(State state) {
        if (currentFileName == null) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Save State");
            fileChooser.setFileFilter(new FileNameExtensionFilter("Serialized State Files (*.ser)", "ser"));

            int result = fileChooser.showSaveDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                String path = file.getAbsolutePath();
                if (!path.endsWith(".ser")) {
                    path += ".ser";
                }
                currentFileName = path;
            } else {
                System.out.println("Save canceled by user.");
                return;
            }
        }

        writeFile(state, currentFileName);
    }


    public State load() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Open State File");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Serialized State Files (*.ser)", "ser"));

        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            return readFile(selectedFile.getAbsolutePath());
        }

        System.out.println("Loading canceled by user.");
        return null;
    }


    public void writeFile(State state, String filePath) {
        try (FileOutputStream fileOut = new FileOutputStream(filePath);
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {

            out.writeObject(state);
            System.out.println("Serialized data is saved in " + filePath);

        } catch (IOException i) {
            i.printStackTrace();
        }
    }


    public State readFile(String fullPath) {
        try (FileInputStream fileIn = new FileInputStream(fullPath);
             ObjectInputStream in = new ObjectInputStream(fileIn)) {

            return (State) in.readObject();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }


    public BufferedImage loadImage(State state) {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                BufferedImage img = ImageIO.read(selectedFile);
                if (img != null) {

                    return img;
                } else {
                    System.out.println("The selected file is not a valid image.");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Image loading canceled by user.");
        }
        return null;
    }
}
