package com.example.log121Lab05.Models.Commands;

import com.example.log121Lab05.Models.Perspective;

public class CopyAllStrategy implements CopyStrategy {
    @Override
    public Perspective copy(Perspective selectedPerspective) {
        return new  Perspective(selectedPerspective);
    }
}
