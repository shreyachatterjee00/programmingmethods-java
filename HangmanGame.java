package com.company;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class HangmanGame{
    private List<String> dictionaryWords;
    private char [] lettersInWord;
    private char [] printArray;
    private int numberOfGuesses = 7;
    private ArrayList<Character> charactersGuessed = new ArrayList<>();
    private int numberIncorrectGuesses;
    private int numberCorrectGuesses;
    private int status = PENDING;

    final static int PENDING = -1;
    final static int WIN = 1;
    final static int LOSE = 0;
    final static String FILENAME = "/Users/shreyachatterjee/IdeaProjects/Project5/words.txt";


    public HangmanGame () throws IOException {
        dictionaryWords = readDictionary();
        String word = randomWord();
        //System.out.println(word);
        lettersInWord = new char[word.length()];
        putLettersInArray(word);
        initalizePrintArray();
        guessWord(word);
    }

    /**
     * reads dictionary
     */
    private List<String> readDictionary () throws IOException {
        BufferedReader buffRead = new BufferedReader(new FileReader(FILENAME));
        List<String> words = new ArrayList<String>();
        String word;
        while (((word = buffRead.readLine()) != null)) {
            words.add(word);
        }
        return words;
    }

    /**
     * pick a random word
     * @return
     */
    private String randomWord () {
        Random randomNumber = new Random();
        int number = randomNumber.nextInt(dictionaryWords.size());
        String word = dictionaryWords.get(number);
        return word;
    }

    public void initalizePrintArray () {
        printArray = new char[lettersInWord.length];
        for (int i = 0; i < printArray.length; i++ ) {
            printArray[i] = ' ';
        }
    }

    /**
     *
     * @return true if guesses are left
     */
    public boolean guessesLeft() {
        if (numberCorrectGuesses + numberIncorrectGuesses < numberOfGuesses) {
            return true;
        }
        else {
            System.out.println("Unfortunately, you are out of guesses. Better luck next time!");
            status = LOSE;
            return false;
        }
    }

    /**
     * initializes lettersInWord array with letters from word
     */
    public void putLettersInArray(String randomWord) {
        for (int i = 0; i < randomWord.length(); i++ ) {
            lettersInWord[i] = randomWord.charAt(i);
        }
    }

    /**
     *
     * @return letter user inputted
     */
    public char askUserForLetter () throws NotALetterException, MoreThanOneCharacterException {
        System.out.println("Please enter a letter: ");
        Scanner scannerObj = new Scanner(System.in);

        String line = scannerObj.nextLine();

        //check if line has multiple characters
        if (line.length()>1) {
            throw new MoreThanOneCharacterException();
        }

        char userLetter = line.charAt(0);
        userLetter = Character.toLowerCase(userLetter);
        if (userLetter < 'a' || userLetter > 'z') {
            throw new NotALetterException();
        }
        return userLetter;
    }

    /**
     * checks if character is in word
     * @param userLetter
     * @return true if character is in word
     */
    public boolean charInWord (char userLetter, String randomWord) {
        for (int i = 0; i < randomWord.length(); i++) {
            if (userLetter == lettersInWord[i]) {
                return true;
            }
        }
        return false;
    }

    /**
     * check if char has already been guessed
     * @return true if char has already been guessed
     */
    public boolean charAlreadyGuessed (char userLetter) {
        for (int i = 0; i < charactersGuessed.size(); i++) {
            if (charactersGuessed.get(i) == userLetter) {
                return true;
            }
        }
        charactersGuessed.add(userLetter);
        return false;
    }

    /**
     * udpates array to include letters guessed
     * returns true if whole word is guessed, false is letters are still missing
     */
    public void updateWord (char userLetter) {
        int index;
        for (int i = 0; i < lettersInWord.length; i++) {
            if (userLetter == lettersInWord[i]) {
                index = i;
                printArray[i] = lettersInWord[i];
            }
        }
    }

    /**
     *
     * @return true if word is guessed, false if letters still need to be guessed
     */
    public boolean isWordGuessed () {
        for (int i = 0; i < lettersInWord.length; i++) {
            if (lettersInWord[i] != printArray[i]) {
                return false;
            }
        }
        System.out.println("Congrats! You guessed the word.");
        status = WIN;
        return true;
    }

    public void printArray () {
        for (int i = 0; i < lettersInWord.length; i++ ) {
            if (printArray[i] == ' ') {
                System.out.print("__" + " ");
            }
            else {
                System.out.print(printArray[i] + " ");
            }
        }
        System.out.print(System.lineSeparator());
    }

    /**
     *
     * @return win = 1, loss = 0, still pending = -1
     */
    public int status () {
        return status;
    }

    /**
     * user plays game, guesses letters
     */
    public void guessWord (String word) {
        System.out.println("Welcome to Hangman! The max guesses allowed is " + numberOfGuesses + ".");
        char chosenLetter = ' ';

        while (guessesLeft() && !isWordGuessed()) {


            //loop until user enters a letter
            boolean flag = false;
            while (flag == false) {
                try {
                    chosenLetter = askUserForLetter();
                    flag = true;
                } catch (NotALetterException exception) {
                    System.out.println("You did not input a letter.");
                }
                catch (MoreThanOneCharacterException exception2) {
                    System.out.println("You inputted multiple letters.");
                }
            }


            if (charInWord(chosenLetter, word) && !charAlreadyGuessed(chosenLetter)) {
                updateWord(chosenLetter);
                numberCorrectGuesses++;

            } else if (charAlreadyGuessed(chosenLetter)) {
                System.out.println("Letter has already been guessed. Guess again.");
            }
            else if (!charInWord(chosenLetter, word)) {
                numberIncorrectGuesses++;
            }
            printArray();
        }
    }




    public static void main(String[] args) {
        try {
            int wins = 0;
            int losses = 0;
            while (true) {
                HangmanGame game = new HangmanGame();
                if (game.status() == HangmanGame.WIN) {
                    wins++;
                }
                else if (game.status() == HangmanGame.LOSE) {
                    losses++;
                }

                System.out.println("Wins: " + wins);
                System.out.println("Losses: " + losses);
                System.out.println("Percentage of wins: " + (wins)*1.0/(wins+losses) * 100); //extra credit
            }
        }
        catch (FileNotFoundException exception) {
            System.out.println("FileNotFound exception.");
        }
        catch (IOException exception) {
            System.out.println("exception" + exception);
        }
    }
    public class NotALetterException extends Exception {}
    public class MoreThanOneCharacterException extends Exception {}
}
