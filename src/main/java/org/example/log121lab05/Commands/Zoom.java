package org.example.log121lab05.Commands;

import org.example.log121lab05.ICommand;
import org.example.log121lab05.Perspective;
import org.example.log121lab05.State;

import java.awt.*;

public class Zoom implements ICommand
{
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
        Perspective active = state.getActivePerspective();
        active.zoom(factor, mouseCoords);
    }
}