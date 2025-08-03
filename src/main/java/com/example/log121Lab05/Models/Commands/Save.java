/*
 * @Author: Yvon Meunier, Aymerik Blais, Simon Boudreau
 * @Date:   2025-07-21 20:00:00
 * @Last Modified by: Yvon Meunier
 * @Last Modified time: 2025-08-02 20:00:00
 */
package com.example.log121Lab05.Models.Commands;

import com.example.log121Lab05.Models.Services.FileService;
import com.example.log121Lab05.Models.State;

/**
 * Save is a Concrete Command which implements the ICommand interface. Its purpose is to create a save file from the current state
 */
public class Save implements ICommand {

    private State state;

    public Save(State s) {
        this.state = s;
    }

    /**
     * Creates a save file from the current state's data through Serialization
     */
    @Override
    public void execute() {
        FileService.writeSave(state);
    }

    @Override
    public void setState(State state) {
        this.state = state;
    }
}
