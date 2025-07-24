package com.example.labo5carry.Models.Commands;

import com.example.labo5carry.Models.Perspective;

public interface CopyStrategy {

    public Perspective copy(Perspective selectedPerspective);

}
