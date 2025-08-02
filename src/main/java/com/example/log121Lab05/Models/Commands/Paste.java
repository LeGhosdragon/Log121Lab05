package com.example.log121Lab05.Models.Commands;

import com.example.log121Lab05.Controller;
import com.example.log121Lab05.Models.Perspective;
import com.example.log121Lab05.Models.State;


public class Paste implements ICommand{

    private State state;
    private PasteType type;

    public Paste(State state, PasteType type) {
        this.state = state;
        this.type = type;
    }

    @Override
    public void execute() {
        Perspective target = Controller.getInstance().getSelectedPerspective();
        Perspective pasted = Controller.getInstance().getPasteBoard();
        state.pastePerspective(target, pasted, type);
    }

    @Override
    public void setState(State state) {
        this.state = state;
    }
}
