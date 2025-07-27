package com.example.log121Lab05.Models.Commands;

import com.example.log121Lab05.Models.Services.FileService;
import com.example.log121Lab05.Models.State;

public class Save implements ICommand {

    private State state;

    public Save(State s) {
        this.state = s;
    }

    @Override
    public void execute() {
        FileService.writeSave(state);
    }

    @Override
    public void setState(State state) {
        this.state = state;
    }
}
