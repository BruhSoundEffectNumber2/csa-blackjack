package com.damon.csa.blackjack;

public class Game {
  ///////////////////////////////
  // Properties
  ///////////////////////////////

  public static final int STARTING_CASH = 100;
  public static final int MIN_BET = 5;

  private InputManager input;
  private DisplayManager output;

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
    output = new DisplayManager(System.out);

    this.numPlayers = numPlayers;
    players = new Player[numPlayers];

    createPlayers();

    start();
  }

  private void createPlayers() {
    dealer = new Player("Gus 'Dealer' Reiber", true);

    for (int i = 0; i < numPlayers; i++) {
      output.print("Player %d, what is your name?", (i + 1));
      String name = input.getTrimmedText();

      players[i] = new Player(name, false);

      output.print("Hello %s, please place your bet.", name);
      output.print("The minimum bet is $%d. You have $%d.", MIN_BET, players[i].cash);
    }
  }

  private void start() {
    deck = new Deck();
    deck.shuffle();
  }

  private void playerBets() {

  }

  private void dealPlayerCards() {

  }

  private void dealDealerHoleCard() {

  }

  private void dealDealerFaceCard() {

  }

  private void playPlayerHands() {

  }

  private void showDealerHoleCard() {

  }

  private void playDealerHand() {

  }

  private void determineWinner() {

  }
}
