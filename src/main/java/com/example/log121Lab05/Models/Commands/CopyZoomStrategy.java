package com.example.log121Lab05.Models.Commands;

import com.example.log121Lab05.Models.Perspective;

public class CopyZoomStrategy implements CopyStrategy {
    @Override
    public Perspective copy(Perspective selectedPerspective) {
        Perspective p  = new Perspective(selectedPerspective.getViewIndex());
        // TODO makes a copy with only the zoom
        return p;
    }
}
