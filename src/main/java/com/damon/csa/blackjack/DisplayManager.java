package com.damon.csa.blackjack;

import java.io.PrintStream;

public class DisplayManager {
  ///////////////////////////////
  // Properties
  ///////////////////////////////

  // ANSI color codes allow you to change the color of the text
  private static final String ANSI_RESET = "\u001B[0m";
  private static final String ANSI_GREEN = "\u001B[32m";
  private static final String ANSI_RED = "\u001B[31m";
  private static final String ANSI_WHITE_BG = "\u001B[47m";

  private static final String[] SUITS = { "S", "H", "D", "C" };
  private static final String[] SPECIAL_RANKS = { "A", "J", "Q", "K" };

  private PrintStream output;

  ///////////////////////////////
  // Constructor
  ///////////////////////////////

  public DisplayManager(PrintStream outputStream) {
    output = outputStream;
  }

  public void clearScreen() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }

  public void print(String format, Object... args) {
    output.println(String.format(format, args));
  }

  public void printHeader(String format, Object... args) {
    output.println(String.format(ANSI_GREEN + format + ANSI_RESET, args));
  }

  public void displayHand(Player player) {
    printHeader("%s's Hand:", player.name);

    for (Card card : player.cards) {
      if (card == null)
        continue;

      System.out.print(ANSI_WHITE_BG);
      System.out.print(SUITS[card.suite]);

      switch (card.rank) {
        case 0:
          System.out.print(SPECIAL_RANKS[0]);
          break;
        case 10:
          System.out.print(SPECIAL_RANKS[1]);
          break;
        case 11:
          System.out.print(SPECIAL_RANKS[2]);
          break;
        case 12:
          System.out.print(SPECIAL_RANKS[3]);
          break;
        default:
          System.out.print(card.rank + 1);
          break;
      }

      System.out.print(ANSI_RESET + " ");

    }

    System.out.print("\n");
  }
}
