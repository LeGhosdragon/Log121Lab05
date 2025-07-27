package com.example.log121Lab05.Models.Commands;

import com.example.log121Lab05.Controller;
import com.example.log121Lab05.Models.Perspective;
import com.example.log121Lab05.Models.State;

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
