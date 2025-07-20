package org.example.log121lab05.Commands;

import org.example.log121lab05.ICommand;
import org.example.log121lab05.Perspective;
import org.example.log121lab05.State;

public class Zoom implements ICommand
{

    private State state = null;

    public Zoom(State state)
    {
        this.state = state;
    }

    @Override
    public void execute() {
        Perspective active = state.getActivePerspective();

                //Listener sur les coords de la souris
                //delta temps de diff entre le début et la fin du scroll
//        active.zoom((int)amount, (Point)sourisPos);
    }
}