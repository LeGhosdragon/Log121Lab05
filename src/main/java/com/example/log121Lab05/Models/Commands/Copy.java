package com.example.log121Lab05.Models.Commands;

import com.example.log121Lab05.Controller;
import com.example.log121Lab05.Models.Perspective;
import com.example.log121Lab05.Models.State;

public class Copy implements ICommand {

    State state;

    public Copy(State state) {
        this.state = state;
    }

    @Override
    public void execute() {
        Controller c = Controller.getInstance();
        Perspective p = c.getSelectedPerspective();
        c.setPasteBoard(p);
    }

    @Override
    public void setState(State state) {
        this.state = state;
    }
}
