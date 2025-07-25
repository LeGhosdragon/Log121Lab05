package com.example.log121Lab05.Models.Commands;

import com.example.log121Lab05.Models.Memento;
import com.example.log121Lab05.Models.State;

public class Undo implements ICommand {

    private State state;
    private Memento memento;

    public Undo(State state, Memento m) {
        this.state = state;
        this.memento = m;
    }


    @Override
    public void execute() {
        state.loadMemento(memento);
    }

    @Override
    public void setState(State state) {
        this.state = state;
    }
}
