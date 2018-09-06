package com.company;
import java.util.Random;

public class RPSGame {

    private int userWins = 0;
    private int computerWins = 0;
    private int ties = 0;

    public enum matchOutcome {
        USERWIN, COMPUTERWIN, TIE
    }

    public enum possibleMoves {
        ROCK, PAPER, SCISSORS
    }


    public int getUserWins() {
        return userWins;
    }

    public int getComputerWins() {
        return computerWins;
    }

    public int getTies() {
        return ties;
    }


    public RPSGame.possibleMoves generateComputerPlay () {
        Random randObj = new Random();
        int move = randObj.nextInt(3);
        return RPSGame.possibleMoves.values()[move];
    }

    public RPSGame.matchOutcome findWinner(RPSGame.possibleMoves userMove, RPSGame.possibleMoves computerMove) {
        if (userMove == possibleMoves.ROCK) {

            if (computerMove == possibleMoves.ROCK) {
                ties++;
                return matchOutcome.TIE;
            }

            else if (computerMove == possibleMoves.PAPER) {
                computerWins++;
                return matchOutcome.COMPUTERWIN;
            }

            else {   //computerMove == possibleMoves.SCISSORS:
                userWins++;
                return matchOutcome.USERWIN;
            }
        }


        else if (userMove == possibleMoves.PAPER) {

            if (computerMove == possibleMoves.ROCK) {
                userWins++;
                return matchOutcome.USERWIN;
            }

            else if (computerMove == possibleMoves.PAPER) {
                ties++;
                return matchOutcome.TIE;
            }

            else { //computerMove == possibleMoves.SCISSORS;
                computerWins++;
                return matchOutcome.COMPUTERWIN;
            }
        }

        else { //userMove == possibleMoves.SCISSORS;

            if (computerMove == possibleMoves.ROCK) {
                computerWins++;
                return matchOutcome.COMPUTERWIN;
            }

            else if (computerMove == possibleMoves.PAPER) {
                userWins++;
                return matchOutcome.USERWIN;
            }

            else  { //computerMove == possibleMoves.SCISSORS;
                ties++;
                return matchOutcome.TIE;
            }
        }
    }


    public static void main(String[] args) {
	 RPSGame game = new RPSGame();
	 System.out.println(game.generateComputerPlay());
    }
}
