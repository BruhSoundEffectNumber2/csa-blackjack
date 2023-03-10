package com.damon.csa.blackjack;

import java.io.InputStream;
import java.util.Scanner;

public class InputManager {
  ///////////////////////////////
  // Properties
  ///////////////////////////////

  private Scanner scanner;

  ///////////////////////////////
  // Constructor
  ///////////////////////////////

  public InputManager(InputStream stream) {
    scanner = new Scanner(stream);
  }

  ///////////////////////////////
  // Methods
  ///////////////////////////////

  // TODO: DisplayManager may be better than directly using System.out

  public void waitForInput() {
    System.out.println("Press Enter to continue...");
    scanner.nextLine();
  }

  /**
   * Gets the next text input, trimming whitespace at the start and end.
   * Works for only one line of text at a time.
   */
  public String getTrimmedText() {
    String raw = scanner.nextLine();

    return raw.trim();
  }

  /**
   * Gets the next number as an integer. If the input is invalid,
   * then the user will be shown the question again.
   */
  public int getNumber() {
    while (true) {
      try {
        String raw = getTrimmedText();
        int num = Integer.parseInt(raw);

        return num;
      } catch (Exception e) {
        System.out.println("Invalid answer. Please enter a number.");
      }
    }
  }

  /**
   * Gets the next number as an integer, that is at least the minimum,
   * and at most the maximum. If the input is invalid,
   * then the user will be shown the question again.
   * 
   * @param min The minimum number that is accepted (inclusive).
   * @param max The maximum number that is accepted (inclusive).
   */
  public int getNumber(int min, int max) {
    while (true) {
      try {
        int result = Integer.parseInt(getTrimmedText());

        if (result < min || result > max) {
          // We still throw an exception because the user entered an invalid number
          throw new Exception();
        }

        return result;
      } catch (Exception e) {
        System.out.println("Invalid answer. Please enter a number between "
            + min + " and " + max);
      }
    }
  }

  /**
   * Prints out a set of decisions, and allows the user to pick one of them.
   * The user can pick by name or by a number, and if they don't pick a valid
   * option, they will be shown the decision again.
   * 
   * @param options An array of options, each a string that is shown to the user.
   * @return The decision the player made, corresponding to the index of the
   *         options passed in.
   */
  public int getDecision(String[] options) {
    // We want to keep asking the player for a valid option
    while (true) {
      for (int i = 0; i < options.length; i++) {
        /*
         * Format allows us to display complicated text without over-using string
         * concatenation.
         * For more details see: https://www.javatpoint.com/java-string-format
         */
        String display = String.format("(%d): %s ", i + 1, options[i]);

        System.out.print(display);
      }
      System.out.print("\n");

      int result = -1;

      String raw = getTrimmedText();

      try {
        // We need to convert our result to be based on 0
        result = Integer.parseUnsignedInt(raw) - 1;
      } catch (Exception e) {
        // The user did not give a number, so check it as a string
        for (int i = 0; i < options.length; i++) {
          if (options[i].equalsIgnoreCase(raw)) {
            result = i;
          }
        }
      }

      // If we have a result that isn't -1, then we got a valid answer
      if (result != -1) {
        // But we need to make sure the result corresponds to an option
        if (result < options.length) {
          return result;
        }
      }

      // We haven't been given a valid answer, so let the user know and try again
      System.out.println("Invalid answer. Please try again.");
    }
  }
}
