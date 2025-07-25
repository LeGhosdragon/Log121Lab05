package com.example.log121Lab05.Models.Commands;

import com.example.log121Lab05.Models.Services.FileService;
import com.example.log121Lab05.Models.State;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class LoadImage implements ICommand {

    private State state;

    public LoadImage(State state) {
        this.state = state;
    }

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
