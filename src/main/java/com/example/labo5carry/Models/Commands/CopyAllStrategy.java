package com.example.labo5carry.Models.Commands;

import com.example.labo5carry.Models.Perspective;

public class CopyAllStrategy implements CopyStrategy {
    @Override
    public Perspective copy(Perspective selectedPerspective) {
        return new  Perspective(selectedPerspective);
    }
}
