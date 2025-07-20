package org.example.log121lab05.Commands;

import org.example.log121lab05.FileHandler;
import org.example.log121lab05.ICommand;
import org.example.log121lab05.State;

public class Save implements ICommand
{

    private State state = null;

    public Save(State state)
    {
        this.state = state;
    }

    @Override
    public void execute()
    {
        FileHandler fh = FileHandler.getInstance();
        fh.save(state);
    }
}