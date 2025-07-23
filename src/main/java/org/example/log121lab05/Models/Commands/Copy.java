package org.example.log121lab05.Models.Commands;

import org.example.log121lab05.Controller;
import org.example.log121lab05.Models.Perspective;
import org.example.log121lab05.Models.State;

public class Copy implements ICommand
{

    private State state = null;

    public Copy(State state)
    {
        this.state = state;
    }

    @Override
    public void execute() {
        Perspective active = state.getActivePerspective();
        Controller.getInstance().setPasteBoard(active.getParams());
    }
}