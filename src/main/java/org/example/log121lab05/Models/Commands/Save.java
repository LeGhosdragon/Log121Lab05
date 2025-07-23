package org.example.log121lab05.Models.Commands;

import org.example.log121lab05.Models.Services.FileHandler;
import org.example.log121lab05.Models.State;

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