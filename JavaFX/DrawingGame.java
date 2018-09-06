package com.company;

import javafx.stage.Stage;
import javafx.application.*;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.text.*;
import javafx.stage.*;
import java.util.*;

public class DrawingGame extends Application {

    private BorderPane pane;
    private Boolean penOn;
    private Text isPenOn;
    private Button clearSpace;
    private Circle drawingSpace;
    private RadioButton color1;
    private RadioButton color2;
    private RadioButton color3;
    private RadioButton thinPen; //extra credit B
    private RadioButton thickPen; //extra credit B
    private Group drawCircles;
    private Button eraser;
    private boolean eraserOn;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        pane = new BorderPane();

        HBox hboxPane = new HBox();

        penOn = false;
        eraserOn = false;

        ToggleGroup colors = new ToggleGroup();
        color1 = new RadioButton("Blue");
        color1.setToggleGroup(colors);
        color1.setFont(Font.font("Georgia"));
        color2 = new RadioButton("Purple");
        color2.setToggleGroup(colors);
        color2.setFont(Font.font("Georgia"));
        color3 = new RadioButton("Pink");
        color3.setToggleGroup(colors);
        color3.setFont(Font.font("Georgia"));

        ToggleGroup drawingCapabilities = new ToggleGroup();        //extra credit B
        thinPen = new RadioButton("Thin Pen");
        thinPen.setFont(Font.font("Georgia"));
        thinPen.setToggleGroup(drawingCapabilities);
        thickPen = new RadioButton("Thick Pen");
        thickPen.setToggleGroup(drawingCapabilities);
        thickPen.setFont(Font.font("Georgia"));

        isPenOn = new Text ("Pen is currently off.");
        isPenOn.setFont(Font.font("Georgia"));

        clearSpace = new Button("Clear Drawing Space");
        clearSpace.setFont(Font.font("Georgia"));
        clearSpace.setOnAction(this::clearScreen);

        eraser = new Button ("Eraser " + (eraserOn ? "on": "off"));
        clearSpace.setFont(Font.font("Georgia"));
        eraser.setOnAction(this::handleEraser);

        hboxPane.getChildren().addAll(color1, color2, color3, isPenOn, clearSpace, thinPen, thickPen, eraser);
        pane.setTop(hboxPane);
        hboxPane.setSpacing(10);


        drawingSpace = new Circle(250, 250, 200, Color.KHAKI);
        drawCircles = new Group();

        pane.getChildren().addAll(drawingSpace);
        pane.setOnMouseClicked(this::handleMouseClick);
        pane.getChildren().addAll(drawCircles);

        Scene scene = new Scene (pane, 800, 500);
        primaryStage.setScene(scene);
        primaryStage.show();


    }

    private void handleDrawingSpace(MouseEvent event) {
        double x = event.getX();
        double y = event.getY();

        if (thinPen.isSelected()) {                 //extra credit B
            Circle circle = new Circle (x, y, 5);
            drawCircles.getChildren().addAll(circle);
            circle.setOnMouseMoved(this::drawingSpaceEraser);

            if (color1.isSelected()) {
                circle.setFill(Color.BLUE);
            }
            else if (color2.isSelected()) {
                circle.setFill(Color.PURPLE);
            }
            else if (color3.isSelected()) {
                circle.setFill(Color.PINK);
            }

        }

        else if (thickPen.isSelected()) {           //extra credit B
            Circle circle = new Circle (x, y, 15);
            drawCircles.getChildren().add(circle);
            circle.setOnMouseMoved(this::drawingSpaceEraser);

            if (color1.isSelected()) {
                circle.setFill(Color.BLUE);
            }
            else if (color2.isSelected()) {
                circle.setFill(Color.PURPLE);
            }
            else if (color3.isSelected()) {
                circle.setFill(Color.PINK);
            }
        }






    }

    private void handleMouseClick (MouseEvent event){
        if (penOn) { //pen is currently on
            penNotOn();
        }
        else if (!eraserOn)  {    //if eraser mode is off, pen can draw
            penOn = true;
            isPenOn.setText("Pen is on.");
            drawingSpace.setOnMouseMoved(this::handleDrawingSpace);
        }

    }

    private void handleNoDrawing (MouseEvent event) {
        //empty, does nothing if pen is off
    }

    private void clearScreen(ActionEvent event) {
        drawCircles.getChildren().removeAll(drawCircles.getChildren());
    }

    private void handleEraser(ActionEvent event) { //Extra Credit A
        eraserOn = !eraserOn;
        if (eraserOn) {
            penNotOn();
            drawingSpace.setOnMouseMoved(this::drawingSpaceEraser);
        }

        eraser.setText("Eraser " + (eraserOn ? "on": "off"));
    }

    private void drawingSpaceEraser(MouseEvent event) {
        if (eraserOn) {
            double x = event.getX();
            double y = event.getY();

            for (int i = 0; i < drawCircles.getChildren().size(); i++) {
                Node node = drawCircles.getChildren().get(i);
                if (node.contains(x, y)) {
                    drawCircles.getChildren().remove(node);
                }
            }
        }
    }

    private void penNotOn () {
        penOn = false;
        isPenOn.setText("Pen is not on.");
        drawingSpace.setOnMouseMoved(this::handleNoDrawing);
    }

}
