/*
 * @Author: Yvon Meunier, Aymerik Blais, Simon Boudreau
 * @Date:   2025-07-21 20:00:00
 * @Last Modified by: Yvon Meunier
 * @Last Modified time: 2025-08-02 20:00:00
 */
package com.example.log121Lab05.Models.Commands;

import com.example.log121Lab05.Controller;
import com.example.log121Lab05.Models.Perspective;
import com.example.log121Lab05.Models.State;

/**
 * Paste is a Concrete Command which implements the ICommand interface. Its purpose is to paste the values, determined by the paste type, of the pasteboard onto the selected perspective
 */
public class Paste implements ICommand{

    private State state;
    private PasteType type;

    public Paste(State state, PasteType type) {
        this.state = state;
        this.type = type;
    }

    /**
     * Pastes the desired values from the pasteboard, determined by the paste type, onto the selected perspective
     */
    @Override
    public void execute() {
        Perspective target = Controller.getInstance().getSelectedPerspective();
        Perspective pasted = Controller.getInstance().getPasteBoard();
        state.pastePerspective(target, pasted, type);
    }

    @Override
    public void setState(State state) {
        this.state = state;
    }
}
