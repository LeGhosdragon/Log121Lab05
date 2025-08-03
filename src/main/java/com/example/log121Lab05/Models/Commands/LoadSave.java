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
 * LoadSave is a Concrete Command which implements the ICommand interface. Its purpose is to load a save file and restore its data
 */
public class LoadSave implements ICommand {

    private State state;

    public LoadSave(State state) {
        this.state = state;
    }

    /**
     * Opens a JFileChooser dialog where the user selects a save file. It then sets the State's values to the respective values found in the save file
     */
    @Override
    public void execute() {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int result = chooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {

            File file = chooser.getSelectedFile();
            State s = FileService.loadSave(file);

            if (s != null) {
                state.setState(s);
            }
        }
    }

    @Override
    public void setState(State state) {

    }
}
