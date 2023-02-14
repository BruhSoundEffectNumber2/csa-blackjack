package com.damon.csa.blackjack;

public class Game {
  ///////////////////////////////
  // Properties
  ///////////////////////////////

  public static final int STARTING_CASH = 100;
  public static final int MIN_BET = 5;

  private InputManager input;

  private int numPlayers;
  private Player[] players;

  /*
   * While the dealer and player's are both instances of the Player class,
   * they maintain very different rules on how they are used by the Game,
   * hence the split.
   */

  private Player dealer;

  private Deck deck;

  ///////////////////////////////
  // Constructor
  ///////////////////////////////

  public Game(int numPlayers) {
    input = new InputManager(System.in);

    this.numPlayers = numPlayers;
    players = new Player[numPlayers];

    createPlayers();

    start();
  }

  private void createPlayers() {
    dealer = new Player("Gus 'Dealer' Reiber", true);

    for (int i = 0; i < numPlayers; i++) {
      System.out.println("Player " + (i + 1) + ", what is your name?");
      String name = input.getTrimmedText();

      players[i] = new Player(name, false);

      System.out.println("Hello " + name + ".");
    }
  }

  private void start() {
    deck = new Deck();
    deck.shuffle();

  }
}
