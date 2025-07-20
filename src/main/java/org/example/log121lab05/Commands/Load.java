package org.example.log121lab05.Commands;

import org.example.log121lab05.FileHandler;
import org.example.log121lab05.ICommand;
import org.example.log121lab05.State;

public class Load implements ICommand
{

    private State state = null;

    public Load(State state)
    {
        this.state = state;
    }

    @Override
    public void execute()
    {
        FileHandler fh = FileHandler.getInstance();
        state.setState(fh.load());
    }
}
