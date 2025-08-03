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

import java.awt.*;

/**
 * Zoom is a Concrete Command which implements the ICommand interface. Its purpose is to zoom on the image inside the selected perspective
 */
public class Zoom implements ICommand {

    private double factor;
    private State state;
    private Point mouseCoords;
    public Zoom(State state, double factor, Point location)
    {
        this.state = state;
        this.factor = factor;
        this.mouseCoords = location;
    }


    /**
     * Zooms on the image inside the selected perspective
     */
    @Override
    public void execute() {
        Perspective perspective = Controller.getInstance().getSelectedPerspective();
        state.zoomPerspective(perspective, factor, mouseCoords);
    }

    @Override
    public void setState(State state) {
        this.state = state;
    }

}
