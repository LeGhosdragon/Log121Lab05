package org.example.log121lab05.Commands;

import org.example.log121lab05.ICommand;
import org.example.log121lab05.Perspective;
import org.example.log121lab05.State;

public class Translate implements ICommand
{

    private State state = null;

    public Translate(State state)
    {
        this.state = state;
    }

    @Override
    public void execute() {
        Perspective active = state.getActivePerspective();

                //Listener sur le delta du dragging de la souris * deltaTemps (pour un smooth dragging)
//        active.translate((int)deltaX,(int)deltaY);
    }
}
