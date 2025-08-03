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
 * Copy is a Concrete Command which implements the ICommand interface. Its purpose is to set the pasteboard to the selected perspective
 */
public class Copy implements ICommand {

    State state;

    public Copy(State state) {
        this.state = state;
    }

    /**
     * Sets the pasteboard to the selected perspective
     */
    @Override
    public void execute() {
        Controller c = Controller.getInstance();
        Perspective p = c.getSelectedPerspective();
        c.setPasteBoard(p);
    }

    @Override
    public void setState(State state) {
        this.state = state;
    }
}
