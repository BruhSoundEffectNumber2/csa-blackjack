package com.damon.csa.blackjack;

import java.io.PrintStream;

public class DisplayManager {
  ///////////////////////////////
  // Properties
  ///////////////////////////////

  private PrintStream output;

  ///////////////////////////////
  // Constructor
  ///////////////////////////////

  public DisplayManager(PrintStream outputStream) {
    output = outputStream;
  }

  public void print(String format, Object... args) {
    output.println(String.format(format, args));
  }
}
