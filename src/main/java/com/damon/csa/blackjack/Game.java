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

    deck = new Deck();
  }

  ///////////////////////////////
  // Methods
  ///////////////////////////////

  public void start() {
    createPlayers();

    deck.shuffle();

    dealPlayerCards();

    dealDealerHoleCard();

    dealDealerFaceCard();

    playPlayerHands();

    showDealerHoleCard();

    playDealerHand();

    output.close();
  }

  private void createPlayers() {
    dealer = new Player("Gus 'Dealer' Reiber", true);

    for (int i = 0; i < numPlayers; i++) {
      output.clearScreen();
      output.printHeader("Getting Player Info");
      output.print("Player %d, what is your name?", (i + 1));
      String name = input.getTrimmedText();

      players[i] = new Player(name, false);

      output.print("Hello %s, please place your bet.", name);
      output.print("The minimum bet is $%d. You have $%d.", MIN_BET, players[i].cash);

      // TODO: What if the player does not have enough to make the minimum bet?
      // TODO: Based on the flowchart, betting might get moved into its own function
      int bet = input.getNumber(MIN_BET, players[i].cash + 1);
      players[i].cash -= bet;
      players[i].bet = bet;
    }
  }

  private void dealPlayerCards() {
    for (int i = 0; i < players.length; i++) {
      players[i].addCard(deck.deal());
      players[i].addCard(deck.deal());
    }
  }

  private void dealDealerHoleCard() {
    dealer.addCard(deck.deal());
  }

  private void dealDealerFaceCard() {
    dealer.addCard(deck.deal());
  }

  private void playPlayerHands() {
    output.displayHand(dealer);
  }

  private void showDealerHoleCard() {

  }

  private void playDealerHand() {

  }

  private void determineWinner() {

  }
}
