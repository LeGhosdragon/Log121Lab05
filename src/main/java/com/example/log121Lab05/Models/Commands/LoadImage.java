/*
 * @Author: Yvon Meunier, Aymerik Blais, Simon Boudreau
 * @Date:   2025-07-21 20:00:00
 * @Last Modified by: Yvon Meunier
 * @Last Modified time: 2025-08-02 20:00:00
 */
package com.example.log121Lab05.Models.Commands;

import com.example.log121Lab05.Models.Services.FileService;
import com.example.log121Lab05.Models.State;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * LoadImage is a Concrete Command which implements the ICommand interface. Its purpose is to load the image from the disk and set the image property within State
 */
public class LoadImage implements ICommand {

    private State state;

    public LoadImage(State state) {
        this.state = state;
    }

    /**
     * Opens a JFileChooser dialog where the user selects an image file. It then sets the image variable within State to the image data
     */
    @Override
    public void execute() {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int result = chooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {

            File file = chooser.getSelectedFile();
            BufferedImage img = FileService.readImageFile(file);

            state.setImage(img);
        }
    }

    @Override
    public void setState(State state) {
        this.state = state;
    }
}
