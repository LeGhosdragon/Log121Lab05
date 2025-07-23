package org.example.log121lab05.Models;

public class Memento
{
    private State state = null;
    public Memento(State state)
    {
        this.state = state;
    }
    public State getState()
    {
        return state;
    }
}
