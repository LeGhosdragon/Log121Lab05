package com.example.log121Lab05.Models.Commands;

import com.example.log121Lab05.Models.Perspective;

public interface CopyStrategy {

    public Perspective copy(Perspective selectedPerspective);

}
