package org.example.log121lab05.Models.Commands;

import org.example.log121lab05.Controller;
import org.example.log121lab05.Models.State;

public class Undo implements ICommand
{

    private State state = null;

    public Undo(State state)
    {
        this.state = state;
    }

    @Override
    public void execute()
    {
        State newState = Controller.getInstance().getPreviousState();
        if(newState != null)
        {
            state.setState(newState);
        }
    }
}