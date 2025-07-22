package org.example.log121lab05;

import javafx.application.Platform;
import javafx.fxml.FXML;
import org.example.log121lab05.Commands.LoadImage;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Controller extends Observator
{
    private static Controller c = null;
    private Point[] pasteBoard = new Point[2];
    private List<Memento> snapshots = new ArrayList<>();
    private List<Memento> redoshots = new ArrayList<>();

    private Controller() {}

    public static synchronized Controller getInstance()
    {
        if (c == null)
            c = new Controller();
        return c;
    }

    //Ceci existe ensemble pour vous donner un exemple de comment ça marche en relation avec le FXML
    @FXML
    protected void onHelloButtonClick()
    {
        System.out.println("Button pressed");
    }

    @FXML
    protected void onLoadImageButtonClick(){
        Memento m = new Memento(State.getState());
        snapshots.add(m);

        LoadImage cmd = new LoadImage();
        cmd.execute();
    }

    @FXML
    protected void onQuitButtonClick(){
        Platform.exit();
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
            snapshots.remove(snapshots.size() - 1);
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
            redoshots.remove(redoshots.size() - 1);
            snapshots.add(nextStateMeme);
            return nextStateMeme.getState();
        }
        return null;
    }

    public Point[] getPasteBoard()
    {
        return pasteBoard;
    }
    public void setPasteBoard(Point[] p)
    {
        pasteBoard = p;
    }
}