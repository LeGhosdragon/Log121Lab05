package org.example.log121lab05.Models.Commands;

import org.example.log121lab05.Models.Perspective;
import org.example.log121lab05.Models.State;

public class Translate implements ICommand
{
    private double deltaX;
    private double deltaY;
    private State state = null;


    public Translate(State state,double deltaX, double deltaY)
    {
        this.state = state;
        this.deltaX = deltaX;
        this.deltaY = deltaY;
    }

    @Override
    public void execute() {
        Perspective active = state.getActivePerspective();
        active.translate(deltaX, deltaY);
    }
}
