package com.example.labo5carry.Models.Commands;

import com.example.labo5carry.Controller;
import com.example.labo5carry.Models.Perspective;
import com.example.labo5carry.Models.State;

public class Copy implements ICommand {

    State state;
    CopyStrategy copyStrategy;

    public Copy(State state, CopyStrategy copyStrategy) {
        this.state = state;
        this.copyStrategy = copyStrategy;
    }

    @Override
    public void execute() {
        Controller c = Controller.getInstance();
        Perspective p = copyStrategy.copy(c.getSelectedPerspective());
        c.setPasteBoard(p);
    }

    @Override
    public void setState(State state) {
        this.state = state;
    }
}
