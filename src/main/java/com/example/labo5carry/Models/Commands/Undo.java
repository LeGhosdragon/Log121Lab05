package com.example.labo5carry.Models.Commands;

import com.example.labo5carry.Controller;
import com.example.labo5carry.Models.Memento;
import com.example.labo5carry.Models.State;

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
