package com.example.log121Lab05.Models.Commands;

import com.example.log121Lab05.Models.State;

public interface ICommand {

    void execute();
    void setState(State state);
}
