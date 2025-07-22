package org.example.log121lab05;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.ScrollEvent;
import org.example.log121lab05.Commands.LoadImage;
import org.example.log121lab05.Commands.Translate;
import org.example.log121lab05.Commands.Zoom;

import java.awt.*;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class Controller extends Observator
{
    private static Controller c = null;
    private Point[] pasteBoard = new Point[2];
    private List<Memento> snapshots = new ArrayList<>();
    private List<Memento> redoshots = new ArrayList<>();

    @FXML
    private ImageView imageView1 = new ImageView();
    @FXML
    private ImageView imageView2 = new ImageView();
    @FXML
    private ImageView imageView3 = new ImageView();

    // mouse drag data
    private double clickX;
    private double clickY;

    private Controller() {
        imageView2.setOnScroll(this::onScroll);
        imageView3.setOnScroll(this::onScroll);
    }

    public static synchronized Controller getInstance()
    {
        if (c == null)
            c = new Controller();
        return c;
    }

    private void saveMemento(){
        Memento m = new Memento(State.getState());
        snapshots.add(m);
    }

    @FXML
    protected void onHelloButtonClick()
    {
        System.out.println("Button pressed");
    }

    @FXML
    protected void onLoadImageButtonClick(){
        saveMemento();
        LoadImage cmd = new LoadImage();
        cmd.execute();
    }

    @FXML
    protected void onQuitButtonClick(){
        Platform.exit();
    }

    @FXML
    protected void onImage1Pressed(){
        State.setActivePerspectiveIndex(0);
        Point mousePoint = MouseInfo.getPointerInfo().getLocation();
        clickX = mousePoint.x;
        clickY = mousePoint.y;
    }

    @FXML
    protected void onImage2Pressed(){
        State.setActivePerspectiveIndex(1);
        Point mousePoint = MouseInfo.getPointerInfo().getLocation();
        clickX = mousePoint.x;
        clickY = mousePoint.y;
    }

    @FXML
    protected void onImageDragged(){
        saveMemento();
        Point mousePoint = MouseInfo.getPointerInfo().getLocation();
        Translate cmd = new Translate((int)(mousePoint.x - clickX), (int)(mousePoint.y - clickY));
        cmd.execute();
    }

    private void onScroll(ScrollEvent e){
        saveMemento();

        double zoomFactor = 1.05;
        if (e.getDeltaY() < 0) {
            zoomFactor = 1 / zoomFactor; // Zoom out
        }

        Zoom cmd = new Zoom(new Point(0, 0), zoomFactor);
        cmd.execute();
    }

    @FXML
    protected void onImageZoom(){
        saveMemento();
        Point mousePoint = MouseInfo.getPointerInfo().getLocation();
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

    public ImageView[] getImageViews(){
        return new ImageView[]{imageView1, imageView2, imageView3};
    }
}