package com.example.labo5carry.Models.Commands;

import com.example.labo5carry.Models.State;

public interface ICommand {

    void execute();
    void setState(State state);
}
