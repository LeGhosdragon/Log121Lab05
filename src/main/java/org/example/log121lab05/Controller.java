package org.example.log121lab05;

import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.StackPane;
import org.example.log121lab05.Commands.LoadImage;
import org.example.log121lab05.Commands.Translate;
import org.example.log121lab05.Commands.Zoom;
import javafx.scene.input.MouseEvent;
import java.awt.*;
import java.awt.Image;

import java.util.ArrayList;
import java.util.List;

public class Controller extends Observator
{
    private static Controller c = null;
    private Point[] pasteBoard = new Point[2];
    private List<Memento> snapshots = new ArrayList<>();
    private List<Memento> redoshots = new ArrayList<>();
    private double clickSceneX;
    private double clickSceneY;

    @FXML
    private StackPane pane1;
    @FXML
    private StackPane pane2;
    @FXML
    private ImageView imageView1;
    @FXML
    private ImageView imageView2;
    @FXML
    private ImageView imageView3;

    private Controller() {}
    @FXML
    public void initialize() {
        pane1.setOnScroll(this::onScroll);
        pane2.setOnScroll(this::onScroll);
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
    public void onImage1Pressed(MouseEvent event) {
        State.getState().setActivePerspectiveIndex(0);
        clickSceneX = event.getSceneX();
        clickSceneY = event.getSceneY();
    }


    @FXML
    protected void onImage2Pressed(MouseEvent event){
        State.getState().setActivePerspectiveIndex(1);
        clickSceneX = event.getSceneX();
        clickSceneY = event.getSceneY();
    }


    @FXML
    public void onImageDragged(MouseEvent event) {
        Perspective perspective = State.getState().getActivePerspective();
        Point[] bounds = perspective.getParams(); // top-left, bottom-right
        int imageX1 = bounds[0].x;
        int imageY1 = bounds[0].y;
        int imageX2 = bounds[1].x;
        int imageY2 = bounds[1].y;
        int viewportWidth = imageX2 - imageX1;
        int viewportHeight = imageY2 - imageY1;

        ImageView imageView = Controller.getInstance().getImageViews()[perspective.getViewIndex()];
        double paneWidth = imageView.getBoundsInLocal().getWidth();
        double paneHeight = imageView.getBoundsInLocal().getHeight();

        double deltaX = (event.getSceneX() - clickSceneX) / paneWidth * viewportWidth;
        double deltaY = (event.getSceneY() - clickSceneY) / paneHeight * viewportHeight;

        Translate cmd = new Translate(State.getState(), deltaX, deltaY);
        cmd.execute();

        // Update click for continuous dragging
        clickSceneX = event.getSceneX();
        clickSceneY = event.getSceneY();
    }



    private void onScroll(ScrollEvent e) {
        saveMemento();

        double zoomFactor = (e.getDeltaY() > 0) ? 1.05 : 0.95;

        Perspective perspective = State.getState().getActivePerspective();
        Point[] bounds = perspective.getParams();
        int imageX1 = bounds[0].x;
        int imageY1 = bounds[0].y;
        int imageX2 = bounds[1].x;
        int imageY2 = bounds[1].y;
        int viewportWidth = imageX2 - imageX1;
        int viewportHeight = imageY2 - imageY1;

        ImageView imageView = Controller.getInstance().getImageViews()[perspective.getViewIndex()];
        javafx.scene.image.Image fxImage = imageView.getImage();

        if (fxImage == null) return;

        double imgW = fxImage.getWidth();
        double imgH = fxImage.getHeight();
        double viewW = imageView.getBoundsInLocal().getWidth();
        double viewH = imageView.getBoundsInLocal().getHeight();

        // Calculate scale ratios used by ImageView
        double ratioX = viewW / imgW;
        double ratioY = viewH / imgH;
        double scale = Math.min(ratioX, ratioY);

        // Compute actual rendered image size
        double renderedW = imgW * scale;
        double renderedH = imgH * scale;

        // Offset from top-left of ImageView to where image is actually drawn
        double offsetX = (viewW - renderedW) / 2;
        double offsetY = (viewH - renderedH) / 2;

        // Mouse position relative to image only
        double mouseX = e.getX() - offsetX;
        double mouseY = e.getY() - offsetY;

        // Map to image coordinate space
        double imageMouseX = imageX1 + (mouseX / renderedW) * viewportWidth;
        double imageMouseY = imageY1 + (mouseY / renderedH) * viewportHeight;

        Point zoomCenter = new Point((int) imageMouseX, (int) imageMouseY);

        Zoom cmd = new Zoom(State.getState(), zoomFactor, zoomCenter);
        cmd.execute();
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