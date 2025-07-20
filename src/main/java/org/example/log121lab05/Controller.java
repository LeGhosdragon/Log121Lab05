package org.example.log121lab05;

import javafx.fxml.FXML;

import java.util.ArrayList;
import java.util.List;

public class Controller extends Observator
{
    private static Controller c = null;

    private List<Memento> snapshots = new ArrayList<>();
    private List<Memento> redoshots = new ArrayList<>();

    private Controller()
    {
        c = this;
    }

    public static synchronized Controller getInstance()
    {
        if (c == null)
            c = new Controller();
        return c;
    }


    @FXML
    protected void onHelloButtonClick()
    {
        //welcomeText.setText("Welcome to JavaFX Application!");
    }
    @Override
    public void update(IObservable obs)
    {
        FXMLHandler.getInstance().update(obs);
    }

    public State getPreviousState()
    {
        if(!snapshots.isEmpty())
        {
            Memento previousStateMeme = snapshots.get(snapshots.size() - 1);
            redoshots.add(previousStateMeme);
            return previousStateMeme.getState();
        }
        return null;
    }
    public State getNextState()
    {
        if(!redoshots.isEmpty())
        {
            Memento nextStateMeme = redoshots.get(redoshots.size() - 1);
            snapshots.add(nextStateMeme);
            return nextStateMeme.getState();
        }
        return null;
    }
}