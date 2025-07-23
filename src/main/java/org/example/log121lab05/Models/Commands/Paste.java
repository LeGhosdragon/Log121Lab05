package org.example.log121lab05.Models.Commands;

import org.example.log121lab05.Controller;
import org.example.log121lab05.Models.Perspective;
import org.example.log121lab05.Models.State;

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