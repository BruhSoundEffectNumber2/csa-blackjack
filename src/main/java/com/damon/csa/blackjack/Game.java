package com.damon.csa.blackjack;

import java.util.ArrayList;

public class Game {
  ///////////////////////////////
  // Properties
  ///////////////////////////////

  public static final int STARTING_CASH = 100;
  public static final int MIN_BET = 5;

  private InputManager input;
  private DisplayManager output;

  private Leaderboard leaderboard;

  private Round currentRound;

  private ArrayList<Player> players;

  ///////////////////////////////
  // Constructor
  ///////////////////////////////

  public Game() {
    this.input = new InputManager(System.in);
    this.output = new DisplayManager(System.out);

    this.leaderboard = new Leaderboard();

    this.players = new ArrayList<>();
  }

  ///////////////////////////////
  // Methods
  ///////////////////////////////

  public void start() {
    output.clearScreen();

    leaderboard.load();

    output.printHeader("Welcome to Blackjack");
    output.displayLeaderboard(leaderboard);
    output.newline();

    output.print("How many people will be playing (1 to 4)?");

    createPlayers(input.getNumber(1, 4));

    while (true) {
      // Play a round
      currentRound = new Round(players, input, output, leaderboard);
      currentRound.play();

      leaderboard.save();

      removeDonePlayers();

      output.clearScreen();

      if (players.size() > 0) {
        output.printHeader("Play Again?");
        output.print("Would you like to play again?");

        if (input.getDecision(new String[] { "Yes", "No" }) == 1) {
          break;
        }
      } else {
        output.printHeader("Game Over");
        output.print("There are no players left who can afford to play.");
        output.print("The game will now end.");
        output.newline();

        input.waitForInput();

        break;
      }
    }

    output.clearScreen();

    output.printHeader("Thank You for Playing!");
  }

  private void createPlayers(int number) {
    for (int i = 0; i < number; i++) {
      String name = "";

      while (true) {
        output.clearScreen();

        output.printHeader("Getting Player Info");
        output.print("Player %d, what is your name?", (i + 1));

        name = input.getTrimmedText();

        if (leaderboard.isNameTaken(name) == false) {
          leaderboard.addNewEntry(name);
          break;
        }

        output.newline();

        output.print("The name %s has been used before. Are you %s?", name, name);
        if (input.getDecision(new String[] { "Yes", "No" }) == 0)
          break;
      }

      players.add(i, new Player(name, false));
    }
  }

  private void removeDonePlayers() {
    Player[] removing = currentRound.getDonePlayers();

    if (removing.length > 0) {
      output.clearScreen();

      output
          .printHeader("The following players do not have enough money to make the minimum bet and are being removed:");

      for (Player player : removing) {
        players.remove(player);
        output.print("%s", player.name);
      }
      output.newline();

      input.waitForInput();
    }
  }
}
