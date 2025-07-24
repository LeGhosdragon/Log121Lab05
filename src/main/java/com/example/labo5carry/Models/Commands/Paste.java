package com.example.labo5carry.Models.Commands;

import com.example.labo5carry.Controller;
import com.example.labo5carry.Models.Perspective;
import com.example.labo5carry.Models.State;

public class Paste implements ICommand{

    private State state;

    public Paste(State state) {
        this.state = state;
    }

    @Override
    public void execute() {
        Perspective target = Controller.getInstance().getSelectedPerspective();
        Perspective pasted = Controller.getInstance().getPasteBoard();
        state.pastePerspective(target, pasted);
    }

    @Override
    public void setState(State state) {
        this.state = state;
    }
}
