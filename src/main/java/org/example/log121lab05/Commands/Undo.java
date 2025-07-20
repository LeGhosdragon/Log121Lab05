package org.example.log121lab05.Commands;

import org.example.log121lab05.Controller;
import org.example.log121lab05.ICommand;
import org.example.log121lab05.State;

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