package org.example.log121lab05.Commands;

import org.example.log121lab05.ICommand;
import org.example.log121lab05.Perspective;
import org.example.log121lab05.State;

public class Translate implements ICommand
{
    private int deltaX;
    private int deltaY;

    public Translate(int deltaX, int deltaY)
    {
        this.deltaX = deltaX;
        this.deltaY = deltaY;
    }

    @Override
    public void execute() {
        Perspective active = State.getActivePerspective();
        active.translate(deltaX, deltaY);
    }
}
