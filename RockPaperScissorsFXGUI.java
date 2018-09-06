package com.company;

import javafx.application.*;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;
import javafx.stage.*;

public class RockPaperScissorsFXGUI extends Application {

    private ImageView computerMoveImageView, userMoveImageView;
    private Image rockImage, paperImage, scissorsImage;
    private Text matchOutcomeText, cWinsText, uWinsText, tieText;
    private Text betText;
    private Button rockButton, paperButton, scissorsButton;
    private HBox labelBox;

    private RPSGame game;

    public void start(Stage primaryStage) {

        //int betAmount = getBetAmount(); // only if completing the extra credit!

        game = new RPSGame();

		/* the image and labels for the computer and user move */
        rockImage = new Image("rock.jpg");
        paperImage = new Image("paper.jpg");
        scissorsImage = new Image("scissors.jpg");

        computerMoveImageView = new ImageView(rockImage);
        computerMoveImageView.setVisible(false); // used to make the initial screen layout appear the same as when the game starts
        userMoveImageView = new ImageView(rockImage);
        userMoveImageView.setVisible(false);
        HBox imageBox = new HBox(computerMoveImageView, userMoveImageView);
        imageBox.setAlignment(Pos.CENTER);
        imageBox.setSpacing(20);

        Text computerLabel = new Text("Computer's Move");
        computerLabel.setFont(Font.font("Helvetica", 14));
        Text yourLabel = new Text("Your Move");
        yourLabel.setFont(Font.font("Helvetica", 14));
        labelBox = new HBox(computerLabel, yourLabel);
        labelBox.setAlignment(Pos.CENTER);
        labelBox.setSpacing(30);
        labelBox.setVisible(false);

		/* the results of each match */
        matchOutcomeText = new Text();
        matchOutcomeText.setFill(Color.GREEN);
        matchOutcomeText.setFont(Font.font("Helvetica", 20));

		/* the buttons for the user's play */
        rockButton = new Button("Play Rock");
        rockButton.setOnAction(this::handleUserPlay);
        paperButton = new Button("Play Paper");
        paperButton.setOnAction(this::handleUserPlay);
        scissorsButton = new Button("Play Scissors");
        scissorsButton.setOnAction(this::handleUserPlay);
        HBox buttonBox = new HBox(rockButton, paperButton, scissorsButton);
        buttonBox.setSpacing(10);
        buttonBox.setAlignment(Pos.CENTER);

		/* the game statistics */
        cWinsText = new Text("Computer Wins: " + game.getComputerWins());
        cWinsText.setFont(Font.font("Helvetica", 16));
        cWinsText.setFill(Color.BLUE);
        uWinsText = new Text("User Wins: " + game.getUserWins());
        uWinsText.setFont(Font.font("Helvetica", 16));
        uWinsText.setFill(Color.BLUE);
        tieText = new Text("Ties: " + game.getTies());
        tieText.setFont(Font.font("Helvetica", 16));
        tieText.setFill(Color.BLUE);
        HBox statsBox = new HBox(cWinsText, uWinsText, tieText);
        statsBox.setSpacing(10);
        statsBox.setAlignment(Pos.CENTER);

        VBox pane = new VBox(imageBox, labelBox, matchOutcomeText, buttonBox, statsBox);
        pane.setAlignment(Pos.CENTER);
        pane.setSpacing(20);
        pane.setStyle("-fx-background-color: white");

        Scene scene = new Scene(pane, 400, 400, Color.TRANSPARENT);
        primaryStage.setTitle("Rock, Paper, Scissors, Go!");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // only implemented this method if completing the extra credit
    private int getBetAmount() {

        // YOUR EXTRA CREDIT CODE HERE
        return 0;
    }

    private void handleUserPlay(ActionEvent event) {
        // to make all aspects of the display visible
        userMoveImageView.setVisible(true);
        computerMoveImageView.setVisible(true);
        labelBox.setVisible(true);

        RPSGame.possibleMoves userMove;

        userMove = updateUserPicture(event);
        RPSGame.possibleMoves computerMove = game.generateComputerPlay();
        updateComputerPicture(computerMove);

        findWinner(userMove, computerMove);
    }

    private RPSGame.possibleMoves updateUserPicture (ActionEvent event) { //get source of click and then update user's picture to show move
        if (event.getSource() == rockButton) {
            userMoveImageView.setImage(rockImage);
            return RPSGame.possibleMoves.ROCK;
        }
        else if (event.getSource() == scissorsButton) {
            userMoveImageView.setImage(scissorsImage);
            return RPSGame.possibleMoves.SCISSORS;
        }
        else  {      //event source is paper button
            userMoveImageView.setImage(paperImage);
            return RPSGame.possibleMoves.PAPER;
        }
    }

    private void updateComputerPicture (RPSGame.possibleMoves computerMove) {         //generate a new move and update computer's picture to show move
        if (computerMove == RPSGame.possibleMoves.ROCK) {
            computerMoveImageView.setImage(rockImage);
        }
        if (computerMove == RPSGame.possibleMoves.PAPER) {
            computerMoveImageView.setImage(paperImage);
        }
        if (computerMove == RPSGame.possibleMoves.SCISSORS) {
            computerMoveImageView.setImage(scissorsImage);
        }

    }

    private void findWinner (RPSGame.possibleMoves userMove, RPSGame.possibleMoves computerMove) { //find winner of match and update standings for wins, ties, losses
        RPSGame.matchOutcome whoWins = game.findWinner(userMove, computerMove);
        if (whoWins == RPSGame.matchOutcome.COMPUTERWIN) {
            matchOutcomeText.setText("Computer wins!");
        }

        if (whoWins == RPSGame.matchOutcome.USERWIN) {
            matchOutcomeText.setText("You win!");
        }

        if (whoWins == RPSGame.matchOutcome.TIE) {
            matchOutcomeText.setText("It's a tie!");
        }

        cWinsText.setText("Computer Wins: " + game.getComputerWins());
        uWinsText.setText("User Wins: " + game.getUserWins());
        tieText.setText("Ties: " + game.getTies());
    }

    public static void main(String[] args) {
        launch(args);
    }
}