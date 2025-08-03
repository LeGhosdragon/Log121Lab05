/*
 * @Author: Yvon Meunier, Aymerik Blais, Simon Boudreau
 * @Date:   2025-07-21 20:00:00
 * @Last Modified by: Yvon Meunier
 * @Last Modified time: 2025-08-02 20:00:00
 */
package com.example.log121Lab05.Models.Commands;

import com.example.log121Lab05.Models.Memento;
import com.example.log121Lab05.Models.State;

/**
 * Undo is a Concrete Command which implements the ICommand interface. Its purpose is to undo the previous action by loading the previous state from the histoty
 */
public class Undo implements ICommand {

    private State state;
    private Memento memento;

    public Undo(State state, Memento m) {
        this.state = state;
        this.memento = m;
    }


    /**
     * Loads the previous state from the history stack
     */
    @Override
    public void execute() {
        state.loadMemento(memento);
    }

    @Override
    public void setState(State state) {
        this.state = state;
    }
}
