package com.example.labo5carry.Models.Commands;

import com.example.labo5carry.Controller;
import com.example.labo5carry.Models.Perspective;
import com.example.labo5carry.Models.State;

import java.awt.*;

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
