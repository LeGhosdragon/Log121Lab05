package com.example.labo5carry;

import com.example.labo5carry.Models.Commands.*;
import com.example.labo5carry.Models.Memento;
import com.example.labo5carry.Models.Perspective;
import com.example.labo5carry.Models.Services.FileService;
import com.example.labo5carry.Models.State;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ContextMenuEvent;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Stack;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.StackPane;

public class Controller implements IObserver, Initializable {

    public ImageView imageView1; // OG IMAGE
    public ImageView imageView2; // perspective 1
    public ImageView imageView3; // perspective 2
    public StackPane pane1;// perp1 pane
    public StackPane pane2; // perp2 pane

    private static Controller instance;

    private ArrayList<ImageView> imageViews;
    private State state;
    private Stack<Memento> history = new Stack<>();
    private ICommand lastCmd = null;
    private Perspective selectedPerspective;

    private Perspective pasteBoard;
    private CopyStrategy copyStrategy;
    
    private double clickSceneX;
    private double clickSceneY;

    private Controller(){}

    @FXML
    protected void loadImage() {

        LoadImage loadImage = new LoadImage(state);
        loadImage.execute();

    }

    @FXML
    protected void onImageDragged(MouseEvent event) {
        Point[] bounds = selectedPerspective.getParams(); // top-left, bottom-right
        int imageX1 = bounds[0].x;
        int imageY1 = bounds[0].y;
        int imageX2 = bounds[1].x;
        int imageY2 = bounds[1].y;
        int viewportWidth = imageX2 - imageX1;
        int viewportHeight = imageY2 - imageY1;

        ImageView imageView = imageViews.get(selectedPerspective.getViewIndex());
        double paneWidth = imageView.getBoundsInLocal().getWidth();
        double paneHeight = imageView.getBoundsInLocal().getHeight();

        double deltaX = (event.getSceneX() - clickSceneX) / paneWidth * viewportWidth;
        double deltaY = (event.getSceneY() - clickSceneY) / paneHeight * viewportHeight;

        history.add(state.createMemento());

        Translate cmd = new Translate(state, deltaX, deltaY);
        cmd.execute();
        lastCmd = cmd;

        // Update click for continuous dragging
        clickSceneX = event.getSceneX();
        clickSceneY = event.getSceneY();
    }

    @FXML
    protected void onPerspective1Clicked(MouseEvent event) {
        selectedPerspective = state.getPerspective1();
        clickSceneX = event.getSceneX();
        clickSceneY = event.getSceneY();
    }

    @FXML
    protected void onPerspective2Clicked(MouseEvent event) {
        selectedPerspective = state.getPerspective2();
        clickSceneX = event.getSceneX();
        clickSceneY = event.getSceneY();
    }

    @FXML
    protected void undo() {
        if (!history.isEmpty()) {
            Undo undo = new Undo(state, history.pop());
            undo.execute();
        }
    }

    @FXML
    protected void redo() {
        if (lastCmd != null) {
            history.add(state.createMemento());
            lastCmd.setState(state);
            lastCmd.execute();
        }
    }


    public void copy() {
        Copy copy = new Copy(state, copyStrategy);
        copy.execute();
    }

    @FXML
    public void copyAll() {
        copyStrategy = new CopyAllStrategy();
        copy();
    }

    @FXML
    public void copyPosition() {
        // set the copy strategy
        copyStrategy = new CopyPositionStrategy();
        copy();
    }

    @FXML
    public void copyZoom() {
        copyStrategy = new CopyZoomStrategy();
        copy();
    }


    @FXML
    public void paste() {
        history.add(state.createMemento());
        Paste paste = new Paste(state);
        paste.execute();
    }

    @FXML
    public void save(ActionEvent actionEvent) {



    }

    @FXML
    public void load(ActionEvent actionEvent) {
    }

    @FXML
    public void quit(ActionEvent actionEvent) {
        Platform.exit();
    }

    public void contextMenu(ContextMenuEvent mouseEvent) {
        // TODO spawn contextual menu
    }

    @FXML
    protected void onHelloButtonClick() {
        System.out.println("FEATURE NOT IMPLEMENTED YET");
    }

    private void onScroll(ScrollEvent e) {

        double zoomFactor = (e.getDeltaY() > 0) ? 1.05 : 0.95;

        Perspective perspective = selectedPerspective;

        if (perspective == null) return;

        Point[] bounds = perspective.getParams();
        int imageX1 = bounds[0].x;
        int imageY1 = bounds[0].y;
        int imageX2 = bounds[1].x;
        int imageY2 = bounds[1].y;
        int viewportWidth = imageX2 - imageX1;
        int viewportHeight = imageY2 - imageY1;

        ImageView imageView = imageViews.get(selectedPerspective.getViewIndex());
        Image fxImage = imageView.getImage();

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

        history.add(state.createMemento());

        Zoom cmd = new Zoom(state, zoomFactor, zoomCenter);
        cmd.execute();
        lastCmd = cmd;
    }

    @Override
    public void update(Observable o, Object arg) {
        // update UI HERE
        // the observable is always state
        // the arg can be an Image, Perspective or State
        if (arg instanceof BufferedImage) {

            Platform.runLater(() -> {
                // update the image
                javafx.scene.image.Image img = SwingFXUtils.toFXImage(((State) o).getImage(), null);
                imageView1.setImage(img);
                imageView2.setImage(img);
                imageView3.setImage(img);
            });

        }

        if (arg instanceof Perspective p) {
            update(p);
        }

        if (arg instanceof State s) {
            // call all the update functions
            // this is usually done when loading a memento
            update(s,s.getPerspective1());
            update(s,s.getPerspective2());
            update(s,s.getImage());
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        state = new State();
        state.addObserver(this);

        imageViews = new ArrayList<>();
        imageViews.add(imageView1);
        imageViews.add(imageView2);
        imageViews.add(imageView3);

        pane1.setOnScroll(this::onScroll);
        pane2.setOnScroll(this::onScroll);
    }

    public void update(Perspective p) {
        Platform.runLater(() -> {
            Image fxImg = SwingFXUtils.toFXImage(state.getImage(), null);

            // Get image-space viewport corners
            Point[] bounds = p.getParams();
            Point topLeft = bounds[0];
            Point bottomRight = bounds[1];

            int minX = Math.min(topLeft.x, bottomRight.x);
            int minY = Math.min(topLeft.y, bottomRight.y);
            int maxX = Math.max(topLeft.x, bottomRight.x);
            int maxY = Math.max(topLeft.y, bottomRight.y);

            int imageViewportWidth = maxX - minX;
            int imageViewportHeight = maxY - minY;

            // Get StackPane (parent of the ImageView)
            ImageView view = imageViews.get(p.getViewIndex());
            StackPane pane = (StackPane) view.getParent();

            double paneWidth = pane.getWidth();
            double paneHeight = pane.getHeight();

            if (paneWidth == 0 || paneHeight == 0) return;

            double scaleX = (double) imageViewportWidth / paneWidth;
            double scaleY = (double) imageViewportHeight / paneHeight;

            double scale = Math.max(scaleX, scaleY);

            int viewportWidth = (int) (paneWidth * scale);
            int viewportHeight = (int) (paneHeight * scale);

            int centerX = (minX + maxX) / 2;
            int centerY = (minY + maxY) / 2;

            int newMinX = centerX - viewportWidth / 2;
            int newMinY = centerY - viewportHeight / 2;

            // Clamp to image bounds
            newMinX = (int)Math.max(0, Math.min(newMinX, fxImg.getWidth() - viewportWidth));
            newMinY = (int)Math.max(0, Math.min(newMinY, fxImg.getHeight() - viewportHeight));

            Rectangle2D viewport = new Rectangle2D(newMinX, newMinY, viewportWidth, viewportHeight);

            view.setImage(fxImg);
            view.setViewport(viewport);
        });
    }

    public Perspective getSelectedPerspective() {
        return selectedPerspective;
    }

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    public void setPasteBoard(Perspective p) {
        pasteBoard = p;
    }
    public Perspective getPasteBoard() {
        return pasteBoard;
    }

}