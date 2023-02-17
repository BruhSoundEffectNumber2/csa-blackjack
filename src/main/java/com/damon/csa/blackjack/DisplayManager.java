package com.damon.csa.blackjack;

import java.io.PrintStream;
import java.io.PrintWriter;

public class DisplayManager {
  ///////////////////////////////
  // Properties
  ///////////////////////////////

  // ANSI color codes allow you to change the color of the text
  private static final String ANSI_RESET = "\u001B[0m";
  private static final String ANSI_GREEN = "\u001B[32m";
  private static final String ANSI_RED = "\u001B[31m";
  private static final String ANSI_WHITE_BG = "\u001B[47m";

  private static final String[] SUITS = { "\u2660", "\u2665", "\u2666", "\u2663" };
  private static final String[] SPECIAL_RANKS = { "A", "J", "Q", "K" };

  private PrintWriter output;

  ///////////////////////////////
  // Constructor
  ///////////////////////////////

  public DisplayManager(PrintStream outputStream) {
    output = new PrintWriter(outputStream, true);
  }

  ///////////////////////////////
  // Methods
  ///////////////////////////////

  public void close() {
    output.close();
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

      // Hearts and Diamonds are colored red
      if (card.suite == 1 || card.suite == 2) {
        System.out.print(ANSI_RED);
      }

      if (card.faceUp == false) {
        /*
         * When we have a face down card, we want to show just the back of it,
         * so we print out 2 empty spaces with a white background, reset the
         * console, then add a space for the next card. Then, go to th next card.
         * 
         * Take note that I am avoiding nesting here. I could have wrapped the entire
         * following block of code in a check for a face up card. However,
         * that would have really started to nest the code to an excessive amount.
         * The case of a face down card only happens once in blackjack, so
         * we check for that case only, and then do some special logic for that,
         * instead of making the very common case harder to read.
         */
        System.out.print("  " + ANSI_RESET + " ");
        continue;
      }

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
