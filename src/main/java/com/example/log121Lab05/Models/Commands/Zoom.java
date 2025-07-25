package com.example.log121Lab05.Models.Commands;

import com.example.log121Lab05.Controller;
import com.example.log121Lab05.Models.Perspective;
import com.example.log121Lab05.Models.State;

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
