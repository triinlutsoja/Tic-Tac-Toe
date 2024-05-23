import java.util.Scanner;
import java.util.Random;

public class Main {
  public static void main(String[] args) {

    String[][] hashtagField = new String[9][11];

    // assign an empty space value " " instead of default "null" in a String 2D array
    for (int i = 0; i < hashtagField.length; i++) {
      for (int j = 0; j < hashtagField[i].length; j++) {
        hashtagField[i][j] = " ";
      }
    }
    
    // printing the empty playing field at the beginning for visual purposes
    printPlayingHashtagField(hashtagField);
    
    // Welcome players
    System.out.println();
    System.out.println("Welcome to the game of Tic Tac Toe!");
    System.out.print("Please enter the name of the player who gets to play the mark 'X': ");
    String player1 = userInput();
    System.out.print("Please enter the name of the player who gets to play the mark 'O': ");
    String player2 = userInput();

    // decide randomly who starts
    Random random = new Random();
    boolean isItPlayer1Turn = random.nextBoolean();
    if (isItPlayer1Turn == true){
      System.out.println(player1 + ", you get to start the game.");
    } else {
      System.out.println(player2 + ", you get to start the game.");
    }

    boolean gameOver = false;
    int counterOfTurns = 0;

    // Loop the action of asking the marks
    while (!gameOver) {
      System.out.println();
      String name = "";
      String mark = "";
      if (isItPlayer1Turn == true){
        name = player1;
        mark = "X";
      } else {
        name = player2;
        mark = "O";
      }
      System.out.print(name + ", please enter the ROW for your mark: ");
      int humanRow = userInputInt();  // human 3x3 count, not code 9x11 count
      System.out.print("Now enter the COLUMN for your mark: ");
      int humanColumn = userInputInt();
      clearScreen();
      
      // translate the mark's location from human language to correct indexes in the 2D array
      int indexI = 0;
      if (humanRow == 1){
        indexI = 1;
      } else if (humanRow == 2){
        indexI = 4;
      } else if (humanRow == 3){
          indexI = 7;
      }
      
      int indexJ = 0;
      if (humanColumn == 1){
        indexJ = 1;
      } else if (humanColumn == 2){
        indexJ = 5;
      } else if (humanColumn == 3){
          indexJ = 9;
      }
  
      
      hashtagField[indexI][indexJ] = mark;
      printPlayingHashtagField(hashtagField);
      counterOfTurns++;
      
      gameOver = checkWinningCombo(hashtagField, gameOver, mark);
      if (gameOver == false && counterOfTurns == 9){
        System.out.print("It's a tie, nobody won the game.");
        gameOver = true;
      } else if (gameOver == true){
        System.out.print("Congratulations, " + name + "! You won!");
      }

      isItPlayer1Turn = !isItPlayer1Turn; // change turns after every turn
    }
  }

  public static String userInput() {
    Scanner scanner = new Scanner(System.in);
    return scanner.nextLine();
  }
  
  public static int userInputInt() {
    Scanner scanner = new Scanner(System.in);
    return scanner.nextInt();
  }

  public static void printPlayingHashtagField(String[][] hashtagField) {
    for (int i = 0; i < hashtagField.length; i++) {
      for (int j = 0; j < hashtagField[i].length; j++) {
        if (i != 2 && i != 5) { 
          if (j == 3 || j == 7){ 
            hashtagField[i][j] = "|";
            System.out.print(hashtagField[i][j]);
          } else {
            System.out.print(hashtagField[i][j]);
          } 
        } else {
          if (j == 3 || j == 7){
            hashtagField[i][j] = "|";
            System.out.print(hashtagField[i][j]);
          } else {
            hashtagField[i][j] = "_";
            System.out.print(hashtagField[i][j]);
          }
        }
      }
      System.out.println();
    }
  
  }

  public static boolean checkWinningCombo(String[][] array, boolean gameStatus, String mark) {
    if (array[1][1].equals(mark) && array[1][5].equals(mark) && array[1][9].equals(mark)){
      gameStatus = !gameStatus;
    } else if (array[4][1].equals(mark) && array[4][5].equals(mark) && array[4][9].equals(mark)){
      gameStatus = !gameStatus;
    } else if (array[7][1].equals(mark) && array[7][5].equals(mark) && array[7][9].equals(mark)){
      gameStatus = !gameStatus;
    } else if (array[1][1].equals(mark) && array[4][1].equals(mark) && array[7][1].equals(mark)){
      gameStatus = !gameStatus;
    } else if (array[1][5].equals(mark) && array[4][5].equals(mark) && array[7][5].equals(mark)){
      gameStatus = !gameStatus;
    } else if (array[1][9].equals(mark) && array[4][9].equals(mark) && array[7][9].equals(mark)){
      gameStatus = !gameStatus;
    } else if (array[1][1].equals(mark) && array[4][5].equals(mark) && array[7][9].equals(mark)){
      gameStatus = !gameStatus;
    } else if (array[1][9].equals(mark) && array[4][5].equals(mark) && array[7][1].equals(mark)){
      gameStatus = !gameStatus;
    } else {
      gameStatus = gameStatus;
    }
    
    return gameStatus;
  }

  public static void clearScreen(){
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }
}
