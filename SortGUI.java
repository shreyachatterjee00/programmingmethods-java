package com.company;

import javafx.application.*;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.text.*;
import javafx.stage.*;


public class SortGUI extends Application {

	private Button sortButton;
	private Pane selectionSortPane, insertionSortPane;
	
	// variables for the display
	private static final int ARRAY_SIZE = 50, BAR_WIDTH = 8, SPACE_APART = 5; 
	private static final int SSORT_Y_START = 120, ISORT_Y_START = 170;

	private static final int MAX = 80, MIN = 1;
	private int[] selectionArray, insertionArray;

	
	public void start(Stage primaryStage) {
		
		sortButton = new Button("Click to take one step in the sort");
		sortButton.setOnAction(this::handleButton);
		HBox buttonBox = new HBox(sortButton);
		buttonBox.setAlignment(Pos.CENTER);
		
		selectionArray = new int[ARRAY_SIZE];
		insertionArray = new int[ARRAY_SIZE];

		// ??? YOUR CODE HERE- FILL THE ARRAYS WITH RANDOM NUMBERS USING MIN AND MAX VARIABLES (DECLARED ABOVE)
		// PUT THE SAME NUMBERS IN BOTH ARRAYS
		// IF COMPLETING EXTRA CREDIT, ENSURE THAT THERE ARE NO DUPLICATE VALUES WITHIN EACH ARRAY
			
		selectionSortPane = new Pane();
		setupSortPane(selectionSortPane, SSORT_Y_START, selectionArray, Color.BLUE);
		
		insertionSortPane = new Pane();
		setupSortPane(insertionSortPane, ISORT_Y_START, insertionArray, Color.RED);

		Text selectionText = new Text("  Selection Sort");
		selectionText.setFont(Font.font(20));
		Text insertionText = new Text("  Insertion Sort");
		insertionText.setFont(Font.font(20));
		
		VBox primaryBox = new VBox(buttonBox, selectionSortPane, selectionText, insertionSortPane, insertionText);
		primaryBox.setStyle("-fx-background-color: white");
		Scene scene = new Scene(primaryBox, 700, 400, Color.TRANSPARENT);
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.setTitle("Sorting GUI");
		primaryStage.show();
	}
	
	private void setupSortPane(Pane pane, int startingY, int[] array, Color color) {
		pane.getChildren().clear();
		int x = 20; 
		for (int index = 0; index < ARRAY_SIZE; index++) {
			Rectangle rect = new Rectangle(x, startingY - array[index], BAR_WIDTH,	array[index]);
			x = x + BAR_WIDTH + SPACE_APART; // Adds width and spaces so bars don't overlap
			rect.setFill(color);
			pane.getChildren().add(rect);
		}
	}

	private int currentNumberOfPlacesSorted = 0;
	private void modifiedSelectionSort() {
		//move through array
		int smallest = selectionArray[currentNumberOfPlacesSorted];
		for (int i = currentNumberOfPlacesSorted; i < selectionArray.length - currentNumberOfPlacesSorted; i++) {
			//find smallest number
			if (selectionArray[i] < smallest) {
				smallest = selectionArray[i];
			}
		}

		swap(selectionArray, smallest, selectionArray[currentNumberOfPlacesSorted]);
		currentNumberOfPlacesSorted++;

 	}

 	private void swap (int [] selectionArray, int a, int b) {
		int number = selectionArray[a];
		selectionArray[a] = selectionArray[b];
		selectionArray[b] = number;
	}
	
	private void modifiedInsertionSort() {
		// ??? YOUR CODE HERE- THIS SHOULD BE ONE SINGLE STEP OF THE INSERTION SORT
	}
	
	
	private void handleButton(ActionEvent event) {
		// ??? YOUR CODE HERE- WHAT HAPPENS EACH TIME THE BUTTON IS CLICKED

		// you might need to call these methods to update the display
		setupSortPane(selectionSortPane, SSORT_Y_START, selectionArray, Color.BLUE);
		setupSortPane(insertionSortPane, ISORT_Y_START, insertionArray, Color.RED);
	}

	
	public static void main(String[] args) {
		launch(args);

	}

}
