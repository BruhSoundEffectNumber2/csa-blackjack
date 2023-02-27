package com.damon.csa.blackjack;

public class Game {
  ///////////////////////////////
  // Properties
  ///////////////////////////////

  public static final int STARTING_CASH = 100;
  public static final int MIN_BET = 5;

  private InputManager input;
  private DisplayManager output;

  private Round currentRound;

  ///////////////////////////////
  // Constructor
  ///////////////////////////////

  public Game() {
    input = new InputManager(System.in);
    output = new DisplayManager(System.out);
  }

  ///////////////////////////////
  // Methods
  ///////////////////////////////

  public void start() {
    output.clearScreen();

    output.printHeader("Blackjack");
    output.print("How many people will be playing (1 to 4)?");

    int numPlayers = input.getNumber(1, 4);

    while (true) {
      // Play a round
      currentRound = new Round(numPlayers, input, output);
      currentRound.play();

      output.clearScreen();

      output.printHeader("Play Again?");
      output.print("Would you like to play again?");

      if (input.getDecision(new String[] { "Yes", "No" }) == 1) {
        break;
      }
    }

    output.clearScreen();

    output.print("Thank You for Playing!");
  }
}
