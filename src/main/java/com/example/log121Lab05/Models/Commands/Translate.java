/*
 * @Author: Yvon Meunier, Aymerik Blais, Simon Boudreau
 * @Date:   2025-07-21 20:00:00
 * @Last Modified by: Yvon Meunier
 * @Last Modified time: 2025-08-02 20:00:00
 */
package com.example.log121Lab05.Models.Commands;

import com.example.log121Lab05.Controller;
import com.example.log121Lab05.Models.State;

/**
 * Translate is a Concrete Command which implements the ICommand interface. Its purpose is to translate the image inside the chosen perspective
 */
public class Translate implements ICommand {
    private double deltaX;
    private double deltaY;
    private State state;

    public Translate(State state, double deltaX, double deltaY) {
        this.state = state;
        this.deltaX = deltaX;
        this.deltaY = deltaY;
    }

    /**
     * Translates the image inside a perspective by a certain amount
     */
    @Override
    public void execute() {
        Controller controller = Controller.getInstance();
        state.translatePerspective(controller.getSelectedPerspective(), deltaX, deltaY);
    }

    @Override
    public void setState(State state) {
        this.state = state;
    }
}
