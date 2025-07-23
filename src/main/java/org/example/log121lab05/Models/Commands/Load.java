package org.example.log121lab05.Models.Commands;

import org.example.log121lab05.Models.Services.FileHandler;
import org.example.log121lab05.Models.State;

public class Load implements ICommand
{
    State state = null;
    public Load(State state) {
        this.state = state;
    }

    @Override
    public void execute()
    {
        FileHandler fh = FileHandler.getInstance();
        state.setState(fh.load());
    }
}
