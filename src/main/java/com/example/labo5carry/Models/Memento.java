package com.example.labo5carry.Models;

public class Memento {

    private State state;

    public Memento(State state) {
        this.state = new State(state);
    }

    public State getState() {
        return state;
    }

}
