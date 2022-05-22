import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Hangman{
  public static void main(String[] args) throws FileNotFoundException{

    Scanner scanner = new Scanner(new File("words_alpha.txt"));   // Loads a file of words.
    Scanner keyboard = new Scanner(System.in);

    List<String> words = new ArrayList<>();   // Create a new list to store those words.

    while (scanner.hasNext()) {
      words.add(scanner.nextLine());
    }

    Random rand = new Random();
    
    String word = words.get(rand.nextInt(words.size()));

    System.out.println(word);


    List<Character> playerGuesses = new ArrayList<>();

    

    int wrongCount = 0;
    while(true) {

      printHangedMan(wrongCount);

      if (wrongCount >= 6) {
        System.out.println("You lose!");
        break;
      }

      printWordState(word, playerGuesses);
      if (!getPlayerGuess(keyboard, word, playerGuesses)) {
        wrongCount++;
        System.out.println("Nope! Try again.");
      }
    
      if(printWordState(word, playerGuesses)) {   // If the player won, stop the game.
        System.out.println("You win!");
        break;
      }
      }
    
  }


  private static void printHangedMan(int wrongCount) {
    System.out.println(" -------");
    System.out.println(" |     |");
    if (wrongCount >= 1) {
      System.out.println(" O");
    }
    if (wrongCount >= 2) {
      System.out.print("\\ ");
      if (wrongCount >= 3) {
        System.out.println("/");
      }
      else {
        System.out.println("");
      }
    }
    if (wrongCount >= 4) {
        System.out.println(" |");
    }
    if (wrongCount >= 5) {
      System.out.print("/ ");
      if (wrongCount >= 6) {
        System.out.println("\\");
      }
      else {
        System.out.println("");
      }
    }
    System.out.println("");
    System.out.println("");
  }

    

  // Gets a player guess
  private static boolean getPlayerGuess(Scanner keyboard, String word, List<Character> playerGuesses) {
    System.out.println("Please enter a letter: ");
    String letterGuess = keyboard.nextLine();
    playerGuesses.add(letterGuess.charAt(0));
    return word.contains(letterGuess);
  }

  // Print the actual state of the game.
  private static boolean printWordState(String word, List<Character> playerGuesses) {
    int correctCount = 0;
    for (int i = 0; i < word.length(); i++) {
      if (playerGuesses.contains(word.charAt(i))) {
        System.out.print(word.charAt(i));
        correctCount++;
      }
      else {
        System.out.print("_");
      }
    }
    System.out.println();
    return (word.length() == correctCount);
  }
}