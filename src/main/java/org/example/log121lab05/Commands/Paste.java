package org.example.log121lab05.Commands;

import org.example.log121lab05.Controller;
import org.example.log121lab05.ICommand;
import org.example.log121lab05.Perspective;
import org.example.log121lab05.State;

public class Paste implements ICommand
{

    private State state = null;

    public Paste(State state)
    {
        this.state = state;
    }

    @Override
    public void execute() {
        Perspective active = state.getActivePerspective();
        active.paste(Controller.getInstance().getPasteBoard());
    }
}