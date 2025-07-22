package org.example.log121lab05.Commands;

import org.example.log121lab05.ICommand;
import org.example.log121lab05.Perspective;
import org.example.log121lab05.State;

import java.awt.*;

public class Zoom implements ICommand
{
    private Point mousePos;
    private int factor;

    public Zoom(Point mousePos, int factor)
    {
        this.mousePos = mousePos;
        this.factor = factor;
    }

    @Override
    public void execute() {
        Perspective active = State.getActivePerspective();
        active.zoom(factor, mousePos);
    }
}