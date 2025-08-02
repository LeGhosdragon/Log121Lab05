/*
 * @Author: mikey.zhaopeng
 * @Email:  admin@example.com
 * @Date:   2016-07-29 15:57:29
 * @Last Modified by: Noscere
 * @Last Modified time: 2022-11-15 06:25:53.546
 * @Description: description
 */
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


    /**
     *
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
