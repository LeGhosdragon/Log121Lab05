package org.example.log121lab05;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.StackPane;
import org.example.log121lab05.Models.Commands.*;
import javafx.scene.input.MouseEvent;
import org.example.log121lab05.Models.Memento;
import org.example.log121lab05.Models.Perspective;
import org.example.log121lab05.Models.State;

import java.awt.*;
import java.net.URL;
import java.util.*;
import java.util.List;

public class Controller extends Observator implements Initializable {
    private static Controller c = null;
    private Point[] pasteBoard = new Point[2];
    private final Stack<Memento> snapshots = new Stack<>();
    private final List<Memento> redoshots = new ArrayList<>();
    private double clickSceneX;
    private double clickSceneY;
    private State currentState;
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

    private ContextMenu contextMenu;

    private Controller() {
    }

    public static synchronized Controller getInstance() {
        if (c == null)
            c = new Controller();
        return c;
    }

    private void saveMemento() {
        Memento m = new Memento(currentState);
        snapshots.add(m);
    }

    @FXML
    protected void onHelloButtonClick() {
        System.out.println("Button pressed");
    }
    @FXML
    protected void undo() {
        saveMemento();
        Undo undo = new Undo(currentState);
        undo.execute();
    }
    @FXML
    protected void redo() {
        saveMemento();
        Redo redo = new Redo(currentState);
        redo.execute();
    }
    @FXML
    protected void save() {
        Save save = new Save(currentState);
        save.execute();
    }
    @FXML
    protected void load() {
        Load load = new Load(currentState);
        load.execute();
    }
    @FXML
    protected void copy() {
        saveMemento();
        Copy copy = new Copy(currentState);
        copy.execute();
    }
    @FXML
    protected void paste() {
        saveMemento();
        Paste paste = new Paste(currentState);
        paste.execute();
    }

    @FXML
    protected void onLoadImageButtonClick() {
        saveMemento();
        LoadImage cmd = new LoadImage();
        cmd.execute();
    }

    @FXML
    protected void onQuitButtonClick() {
        Platform.exit();
    }

    @FXML
    public void onImage1Pressed(MouseEvent event) {
        currentState.setActivePerspectiveIndex(0);
        clickSceneX = event.getSceneX();
        clickSceneY = event.getSceneY();
    }


    @FXML
    protected void onImage2Pressed(MouseEvent event) {
        currentState.setActivePerspectiveIndex(1);
        clickSceneX = event.getSceneX();
        clickSceneY = event.getSceneY();
    }


    @FXML
    public void onImageDragged(MouseEvent event) {
        Perspective perspective = currentState.getActivePerspective();
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

        Translate cmd = new Translate(currentState, deltaX, deltaY);
        cmd.execute();

        // Update click for continuous dragging
        clickSceneX = event.getSceneX();
        clickSceneY = event.getSceneY();
    }


    private void onScroll(ScrollEvent e) {
        saveMemento();

        double zoomFactor = (e.getDeltaY() > 0) ? 1.05 : 0.95;

        Perspective perspective = currentState.getActivePerspective();
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

        Zoom cmd = new Zoom(currentState, zoomFactor, zoomCenter);
        cmd.execute();
    }

    @Override
    public void update(IObservable obs) {
        FXMLHandler.getInstance().update(obs);
    }

    public State getPreviousState() {
        if (!snapshots.isEmpty()) {
            Memento previousStateMeme = snapshots.pop();
            redoshots.add(previousStateMeme);
            return previousStateMeme.getState();
        }
        return null;
    }

    public State getNextState() {
        if (!redoshots.isEmpty()) {
            Memento nextStateMeme = redoshots.get(redoshots.size() - 1);
            redoshots.remove(redoshots.size() - 1);
            snapshots.add(nextStateMeme);
            return nextStateMeme.getState();
        }
        return null;
    }

    public Point[] getPasteBoard() {
        return pasteBoard;
    }

    public void setPasteBoard(Point[] p) {
        pasteBoard = p;
    }

    public ImageView[] getImageViews() {
        return new ImageView[]{imageView1, imageView2, imageView3};
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pane1.setOnScroll(this::onScroll);
        pane2.setOnScroll(this::onScroll);
        currentState = new State();

        // context menu stuff
        MenuItem item1 = new MenuItem("Copy");
        MenuItem item2 = new MenuItem("Paste");
        contextMenu = new ContextMenu();

        contextMenu.getItems().addAll(item1, item2);

        item1.setOnAction(actionEvent -> {
            copy();
        });

        item2.setOnAction(actionEvent -> {
            paste();
        });

    }

    public void contextMenu(Event e) {
        if (e instanceof ContextMenuEvent) {
            // TODO fix the position of the context menu
            contextMenu.show(imageView2, ((ContextMenuEvent) e).getSceneX(), ((ContextMenuEvent) e).getSceneY());
        }

    }

}