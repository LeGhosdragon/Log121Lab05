package com.example.labo5carry.Models.Commands;

import com.example.labo5carry.Controller;
import com.example.labo5carry.Models.State;

public class Copy implements ICommand {

    State state;

    public Copy(State state) {
        this.state = state;
    }

    @Override
    public void execute() {
        Controller c = Controller.getInstance();
        c.setPasteBoard(c.getSelectedPerspective().getParams());
    }

    @Override
    public void setState(State state) {
        this.state = state;
    }
}
