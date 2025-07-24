package com.example.log121Lab05.Models;

public class Memento {

    private State state;

    public Memento(State state) {
        this.state = new State(state);
    }

    public State getState() {
        return state;
    }

}
