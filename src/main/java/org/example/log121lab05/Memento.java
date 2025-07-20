package org.example.log121lab05;

public class Memento
{
    private State state = null;
    Memento(State state)
    {
        this.state = state;
    }
    public State getState()
    {
        return state;
    }
}
