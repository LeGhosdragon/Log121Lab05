/*
 * @Author: Yvon Meunier, Aymerik Blais, Simon Boudreau
 * @Date:   2025-07-21 20:00:00
 * @Last Modified by: Yvon Meunier
 * @Last Modified time: 2025-08-02 20:00:00
 */
package com.example.log121Lab05.Models;

/**
 * Memento is a class which holds a copy of the given state, typically the current state,  as a backup which can be loaded whenever
 */
public class Memento {

    private State state;

    public Memento(State state) {
        this.state = new State(state);
    }

    public State getState() {
        return state;
    }

}
