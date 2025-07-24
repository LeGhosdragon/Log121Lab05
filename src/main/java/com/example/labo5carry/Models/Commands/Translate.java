package com.example.labo5carry.Models.Commands;

import com.example.labo5carry.Controller;
import com.example.labo5carry.Models.State;

public class Translate implements ICommand {
    private double deltaX;
    private double deltaY;
    private State state;

    public Translate(State state, double deltaX, double deltaY) {
        this.state = state;
        this.deltaX = deltaX;
        this.deltaY = deltaY;
    }

    @Override
    public void execute() {
        Controller controller = Controller.getInstance();
        state.translatePerspective(controller.getSelectedPerspective(), deltaX, deltaY);
    }

    @Override
    public void setState(State state) {
        this.state = state;
    }
}
