package com.damon.csa.blackjack;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.Comparator;

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
  private static final String[] RANKS = { "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K" };

  private PrintStream output;

  ///////////////////////////////
  // Constructor
  ///////////////////////////////

  public DisplayManager(PrintStream outputStream) {
    output = outputStream;
  }

  ///////////////////////////////
  // Methods
  ///////////////////////////////

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

  public void newline() {
    System.out.print("\n");
  }

  public void displayHand(Player player) {
    String handValueText = "?";

    if (!player.hand.hasFaceDownCard()) {
      handValueText = Integer.toString(player.hand.getValue());
    }

    print("%s's Hand (Value: %s):", player.name, handValueText);

    for (Card card : player.hand.getCards()) {
      System.out.print(ANSI_WHITE_BG);

      // Hearts and Diamonds are colored red
      if (card.suit == 1 || card.suit == 2) {
        System.out.print(ANSI_RED);
      }

      if (card.faceUp == false) {
        /*
         * When we have a face down card, we want to show just the back of it,
         * so we print out 2 empty spaces with a white background, reset the
         * console, then add a space for the next card. Then, go to the next card.
         */
        System.out.print("  " + ANSI_RESET + " ");
        continue;
      }

      System.out.print(SUITS[card.suit] + RANKS[card.rank]);

      System.out.print(ANSI_RESET + " ");

    }

    System.out.print("\n");
  }

  public void displayLeaderboard(Leaderboard leaderboard) {
    LeaderboardEntry[] entries = leaderboard.getEntries();

    Arrays.sort(entries, new Comparator<>() {
      /*
       * When we create our own object, Java can't know how it should be
       * sorted, so it leaves that to us. In this case, we use the money
       * the player won as the metric for sorting.
       */
      @Override
      public int compare(LeaderboardEntry o1, LeaderboardEntry o2) {
        // Standard convention is to return -1 when o1 < o2, but we want the highest
        // first, so we invert that
        if (o1.moneyWon < o2.moneyWon)
          return 1;
        if (o1.moneyWon > o2.moneyWon)
          return -1;
        return 0;
      }
    });

    printHeader("Leaderboard - Top 10:");

    for (int i = 0; i < Math.min(10, entries.length); i++) {
      print("%d - %s has %s $%d",
          i + 1,
          entries[i].name,
          // When we have net lost money, insert "lost" instead of "won"
          Math.signum(entries[i].moneyWon) < 0 ? "lost" : "won",
          // We already imply a sign with the word before this
          Math.abs(entries[i].moneyWon));
    }
  }
}
