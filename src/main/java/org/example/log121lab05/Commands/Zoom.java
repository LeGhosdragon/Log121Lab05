package org.example.log121lab05.Commands;

import org.example.log121lab05.ICommand;
import org.example.log121lab05.State;

public class Zoom implements ICommand
{

    private State state = null;

    public Zoom(State state)
    {
        this.state = state;
    }

    @Override
    public void execute()
    {

    }
}