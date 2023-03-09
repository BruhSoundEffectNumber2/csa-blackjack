package com.damon.csa.blackjack;

public class Round {
  ///////////////////////////////
  // Properties
  ///////////////////////////////
  private int numPlayers;

  // The index of the players and bets are matched, bets[0] gives the bet of
  // player[0]
  private Player[] players;
  private int[] bets;

  private Player dealer;
  private Deck deck;

  private InputManager input;
  private DisplayManager output;

  ///////////////////////////////
  // Constructor
  ///////////////////////////////

  public Round(int numPlayers, InputManager input, DisplayManager output) {
    this.numPlayers = numPlayers;
    this.input = input;
    this.output = output;

    this.players = new Player[numPlayers];
    this.bets = new int[numPlayers];

    this.deck = new Deck();
  }

  ///////////////////////////////
  // Methods
  ///////////////////////////////

  public void play() {
    createPlayers();

    deck.shuffle();

    dealCards();

    for (Player player : players) {
      playerMove(player);
    }
  }

  private void createPlayers() {
    dealer = new Player("Gus 'Dealer' Reiber", true);

    for (int i = 0; i < numPlayers; i++) {
      output.clearScreen();

      output.printHeader("Getting Player Info");
      output.print("Player %d, what is your name?", (i + 1));

      String name = input.getTrimmedText();

      players[i] = new Player(name, false);

      // TODO: What if the player does not have enough to make the minimum bet?
      output.print("Hello %s, please place your bet.", name);
      output.print("The minimum bet is $%d. You have $%d.", Game.MIN_BET, players[i].cash);

      int bet = input.getNumber(Game.MIN_BET, players[i].cash);
      players[i].cash -= bet;
      bets[i] = bet;
    }
  }

  private void dealCards() {
    for (Player player : players) {
      player.hand.addCard(deck.deal());
      player.hand.addCard(deck.deal());
    }

    // Because this is the only time we deal a card facing down, we just
    // store the card in a temp variable and set it to be face down
    Card faceDownCard = deck.deal();
    faceDownCard.faceUp = false;
    dealer.hand.addCard(faceDownCard);

    dealer.hand.addCard(deck.deal());
  }

  private void playerMove(Player player) {
    output.clearScreen();

    output.printHeader("%s's Turn", player.name);
    output.displayHand(player);
    output.newline();

    int choice = input.getDecision(new String[] { "Hit", "Stand" });
    output.newline();

    
  }
}
