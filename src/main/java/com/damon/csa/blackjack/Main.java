package com.damon.csa.blackjack;

public class Main {
  public static void main(String[] args) {
    /*
     * Right now, we only care about the actual logic of Blackjack,
     * so supporting multiple players is irrelevant. In the future,
     * that topic may be discussed, but there are many options.
     */
    new Game(1).start();

    /*
     * InputManager in = new InputManager(System.in);
     * DisplayManager out = new DisplayManager(System.out);
     * 
     * out.printHeader("Trimmed text");
     * System.out.println(in.getTrimmedText());
     * 
     * out.printHeader("Any number");
     * System.out.println(in.getNumber());
     * 
     * out.printHeader("Any number between -10 and 10");
     * System.out.println(in.getNumber(-10, 11));
     * 
     * for (int i = 0; i < 2; i++) {
     * out.printHeader("For loop %d", i);
     * 
     * out.printHeader("Trimmed text");
     * System.out.println(in.getTrimmedText());
     * 
     * out.printHeader("Any number");
     * System.out.println(in.getNumber());
     * 
     * out.printHeader("Any number between -10 and 10");
     * System.out.println(in.getNumber(-10, 11));
     * }
     */
  }
}